package model;

/**
 * The player in the space invaders game.
 */
public class Player extends GameObject {
    public static final int WIDTH = 46;
    public static final int HEIGHT = 25;

    /** The distance to move when it is time to move. */
    public static final int MOVE_DISTANCE = 15;

    /** The decrease in the score every time hit. */
    public static final int HIT_DECREMENT = 20;

    /* The initial number of lives for the Player. */
    public static final int INITIAL_NUM_LIVES = 4;

    /* The number of lives remaining for the Player. */
    protected int lives;

    /* The current score for the Player. */
    protected int score;

    /** How frequently (in terms of ticks) the player is to change image. */
    public static final int CHANGE_FREQ = 0;

    /**
     * Initialize the player.
     */
    public Player(int x, int y, Game game) {
        super(x, y, game, "player");
        width = WIDTH;
        height = HEIGHT;
        lives = INITIAL_NUM_LIVES;
        score = 0;
    }

    /**
     * No actions for the player at clock ticks.
     */
    protected void update() {}

    /**
     * Move to the left.
     */
    public void moveLeft() {
        x = Math.max(x - MOVE_DISTANCE, 0);
    }

    /**
     * Move to the right.
     */
    public void moveRight() {
        x = Math.min(x + MOVE_DISTANCE, game.getWidth() - width);
    }

    /**
     * If canFire, fire a laser.
     */
    public void fire() {
        if (game.laser == null) {
            int laserX = x + (width - Laser.WIDTH) / 2;
            int laserY = y - Laser.HEIGHT;
            game.addLaser(new Laser(laserX, laserY, game));
        }
    }

    /**
     * Handle the collision with another object.
     * 
     * @param other the object that collided with this instance
     */
    protected void collide(GameObject other) {
        lives = lives - 1;
        moveToLeftSide();
        if (lives == 0) {
            isDead = true;
        }
        score = score - HIT_DECREMENT;
    }

    /**
     * Move to the left side.
     */
    public void moveToLeftSide() {
        x = 0;
    }

    /**
     * @return the number of lives still remaining
     */
    public int getLives() {
        return lives;
    }

    /**
     * Set a new value for the number of lives.
     * 
     * @param lives the new value for the lives field
     */
    public void setLives(int lives) {
        this.lives = lives;
        if (lives == 0) {
            isDead = true;
        }
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param amount the amount by which the score is to be increased
     */
    public void increaseScore(int amount) {
        score = score + amount;
    }
}
