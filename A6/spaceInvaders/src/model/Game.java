package model;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * The model of the space invaders game.
 */
public class Game implements GameControl, GameInfoProvider {
    /** The objects move and change shape only UPDATE_RATE ticks of the clock. */
    public static final int UPDATE_RATE = 45;

    public static final int INVADER_ROWS = 5;
    public static final int INVADER_COLUMNS = 11;
    public static final int INVADER_X_SPACING = 5;
    public static final int INVADER_Y_SPACING = 10;

    /** The top row invaders are worth MAX_KILL_WORTH for each kill. */
    public static final int MAX_KILL_WORTH = 50;
    /** Each row of invaders is worth KILL_WORTH_ROW_DECREMENT less. */
    public static final int KILL_WORTH_ROW_DECREMENT = 10;

    public static final int NUM_BARRICADES = 4;
    public static final int BARRICADE_Y_OFFSET_FROM_BOTTOM = 100;
    public static final int PLAYER_Y_OFFSET_FROM_BOTTOM = 25;

    /** The width of the playing field. */
    protected int width;

    /** The height of the playing field. */
    protected int height;

    /** The object to represent the player of the game. */
    protected Player player;

    /** The object to represent a laser shot by the player. */
    protected Laser laser;

    /** The list of the invaders. */
    protected List<Invader> invadersList;

    /** The list of the blocks. */
    protected List<Block> blocksList;

    /** The list of missiles shot by the invaders. */
    protected List<Missile> missilesList;

    /** The list of explosions as a missile or laser hits something. */
    protected List<Explosion> explosionsList;

    /** The list of observers to be notified whenever the game changes. */
    protected List<GameObserver> observers;

    /** The current tick of the clock. */
    protected int tick;

    /** Has the game been paused? */
    protected boolean paused;

    /** Is the game over because the player was killed? */
    protected boolean over;

    /** The current level in the game. */
    protected int level;


    /**
     * Initialize, but not start, the game.
     */
    public Game(int width, int height) {
        this.width = width;
        this.height = height;

        invadersList = new LinkedList<Invader>();
        blocksList = new LinkedList<Block>();
        missilesList = new LinkedList<Missile>();
        explosionsList = new LinkedList<Explosion>();
        observers = new LinkedList<GameObserver>();

        tick = 0;
        paused = false;
        over = false;

        /* Create the player in the bottom left hand corner. */
        int x = 0;
        int y = height - (Player.HEIGHT + PLAYER_Y_OFFSET_FROM_BOTTOM);
        player = new Player(x, y, this);

        /*
         * Each barricade is 3 blocks high and 4 blocks wide. Use equal gaps between the barricades
         * and on both sides.
         */
        int barricadeWidth = 4 * Block.WIDTH;
        int gap = (width - NUM_BARRICADES * barricadeWidth) / (NUM_BARRICADES + 1);
        for (int i = 0; i < NUM_BARRICADES; i++) {
            /* (x, y) is to be set to the top center of each barricade. */
            y = height - BARRICADE_Y_OFFSET_FROM_BOTTOM - 3 * Block.HEIGHT;
            x = gap + i * (barricadeWidth + gap) + 2 * Block.WIDTH;
            addBarricade(x, y);
        }

        level = 0; // since initializeNextLevel increments the level
        initializeNextLevel();
    }

    /**
     * Add a barricade with (xTopCenter, yTopCenter) as the coordinates for the top center of the
     * barricade.
     */
    private void addBarricade(int xTopCenter, int yTopCenter) {
        int bw = Block.WIDTH;
        int bh = Block.HEIGHT;

        int y = yTopCenter;
        int x = xTopCenter - 2 * bw;
        Block block = new Block(x, y, this, "block_left_corner");
        blocksList.add(block);

        x = x + bw;
        block = new Block(x, y, this, "block_full");
        blocksList.add(block);

        x = x + bw;
        block = new Block(x, y, this, "block_full");
        blocksList.add(block);

        x = x + bw;
        block = new Block(x, y, this, "block_right_corner");
        blocksList.add(block);

        y = y + bh;
        x = xTopCenter - 2 * bw;
        for (int j = 0; j < 4; j++) {
            block = new Block(x, y, this, "block_full");
            blocksList.add(block);
            x = x + bw;
        }

        y = y + bh;
        x = xTopCenter - 2 * bw;
        block = new Block(x, y, this, "block_full");
        blocksList.add(block);

        x = x + 3 * bw;
        block = new Block(x, y, this, "block_full");
        blocksList.add(block);

    }

    /**
     * To start a new level, increment the level number and generate a new set of invaders.
     */
    private void initializeNextLevel() {
        level = level + 1;
        laser = null;
        player.moveToLeftSide();
        for (int i = 0; i < INVADER_ROWS; i++) {
            for (int j = 0; j < INVADER_COLUMNS; j++) {
                int x = (j + 1) * (Invader.WIDTH + INVADER_X_SPACING);
                int y = (i + 1) * (Invader.HEIGHT + INVADER_Y_SPACING);
                int killWorth = MAX_KILL_WORTH - (i * KILL_WORTH_ROW_DECREMENT);
                Invader invader = new Invader(x, y, killWorth, level, this);
                invadersList.add(invader);
            }
        }
    }

    /**
     * Start the game running on a new Thread.
     */
    public void start() {
        Thread t = new Thread() {
            @Override
            public void run() {
                runGame();
            }
        };
        t.setName("Game Thread");
        t.start();
    }

    /**
     * Run the game until it is paused or it is over.
     */
    private void runGame() {
        while (!over && !paused) {
            long start = System.currentTimeMillis();
            update();
            notifyObservers();
            long timeRequiredForStep = System.currentTimeMillis() - start;
            try {
                if (timeRequiredForStep < UPDATE_RATE) {
                    Thread.sleep(UPDATE_RATE - timeRequiredForStep);
                } else {
                    System.out.println("Time for the step at time " + tick + " was "
                            + timeRequiredForStep);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * An object whose lock is used to synchronize the update and painting of invaders.
     */
    private Object invaderSynchronizationObject;

    /**
     * Set the invaderSynchronizationObject instance variable.
     */
    public void setInvaderSynchronizationObject(Object reference) {
        invaderSynchronizationObject = reference;
    }

    /**
     * Update the game model for another tick.
     */
    protected void update() {
        tick = tick + 1;

        player.update();

        Iterator<Explosion> explosionIterator = explosionsList.iterator();
        while (explosionIterator.hasNext()) {
            Explosion explosion = explosionIterator.next();
            explosion.update();
            if (explosion.isDead())
                explosionIterator.remove();
        }

        if (laser != null) {
            laser.update();
            if (laser.isDead())
                laser = null;
        }

        Iterator<Missile> missileIterator = missilesList.iterator();
        while (missileIterator.hasNext()) {
            Missile missile = missileIterator.next();
            if (!missile.isDead())
                missile.update();
            if (missile.isDead())
                missileIterator.remove();
        }

        synchronized (invaderSynchronizationObject) {
            Iterator<Invader> invaderIterator = invadersList.iterator();
            while (invaderIterator.hasNext()) {
                Invader invader = invaderIterator.next();
                if (!invader.isDead())
                    invader.update();
                if (invader.isDead())
                    invaderIterator.remove();
            }
        }

        /* A block doesn't need an update, but may already be dead. */
        Iterator<Block> blockIterator = blocksList.iterator();
        while (blockIterator.hasNext())
            if (blockIterator.next().isDead())
                blockIterator.remove();

        if (isLossState())
            over = true;
        else if (invadersList.size() == 0) {
            player.setLives(player.getLives() + 1);
            initializeNextLevel();
        }
    }

    /**
     * Has the game been lost? The game is lost if the player is dead or an invader crosses the
     * vertical level of the player.
     * 
     * @return is this a loss situation?
     */
    protected boolean isLossState() {
        if (player.isDead())
            return true;

        // determine the position for an invader to cross the level of the player
        int pos = player.getY() - Invader.HEIGHT;
        Iterator<Invader> iterator = invadersList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getY() > pos)
                return true;
        }

        return false;
    }

    /**
     * Switch the state of the paused field and take appropriate action for the change in the paused
     * status.
     */
    public void togglePaused() {
        paused = !paused;
        if (!paused)
            start();
        else
            notifyObservers();
    }

    /**
     * Add observer to the list of observers.
     * 
     * @param observer an observer of the game
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Invoke gameChanged on all the observers of the game.
     */
    private void notifyObservers() {
        for (GameObserver obs : observers) {
            obs.gameChanged();
        }
    }

    /**
     * @return a list of all GameObjects in the game
     */
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new LinkedList<GameObject>();
        gameObjects.add(player);

        for (Invader invader : invadersList)
            gameObjects.add(invader);

        for (Block block : blocksList)
            gameObjects.add(block);

        for (Missile missile : missilesList)
            gameObjects.add(missile);

        if (laser != null)
            gameObjects.add(laser);

        for (Explosion explosion : explosionsList)
            gameObjects.add(explosion);

        return gameObjects;
    }

    /**
     * Add a missile to the game.
     * 
     * @param missile the missile to be added to the game
     */
    protected void addMissile(Missile missile) {
        missilesList.add(missile);
    }

    /**
     * Add a laser to the game.
     * 
     * @param laser the laser to be added to the game
     */
    protected void addLaser(Laser laser) {
        if (this.laser != null)
            throw new RuntimeException("Cannot shoot a laser when one already exists.");

        this.laser = laser;
    }

    /**
     * Add an explosion to the game.
     * 
     * @param explosion the explosion to be added to the game
     */
    protected void addExplosion(Explosion explosion) {
        explosionsList.add(explosion);
    }

    /**
     * @return the current tick count
     */
    protected int getTicks() {
        return tick;
    }

    /**
     * @return is the game paused?
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @return the current level in the game
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return is the game over?
     */
    public boolean isOver() {
        return over;
    }

    /**
     * @return the width of the game
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the game
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the player's score
     */
    public int getPlayerScore() {
        return player.getScore();
    }

    /**
     * @return the number of lives of the player
     */
    public int getPlayerLives() {
        return player.getLives();
    }

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return the current time
     */
    public int getTick() {
        return tick;
    }
}
