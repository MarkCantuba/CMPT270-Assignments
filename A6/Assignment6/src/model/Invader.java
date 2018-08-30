package model;

import util.RandomNumberGenerator;

/**
 * Model an invader as a special type of GameObject.
 */
public class Invader extends GameObject {
    public static final int WIDTH = 35;
    public static final int HEIGHT = 35;

    public static final int HORIZONTAL_MOVE_DIST = 8;
    public static final int MIN_VERTICAL_MOVE_DIST = 14;
    public static final int VERTICAL_SPEEDUP_PER_LEVEL = 6;
    protected int verticalMoveDistance;


    /** The probability of firing at each clock tick. */
    protected static final float FIRE_PROBABILITY = .001f;

    /** The value to the player of killing this invader. */
    protected int killWorth;

    protected int moveDirection = MOVE_RIGHT;
    public static final int MOVE_LEFT = -1;
    public static final int MOVE_RIGHT = 1;

    /** The tick of the clock for the next time when all invaders switch direction and advance. */
    public static int changeDirectionTick;

    /** How frequently (in terms of ticks) the invader is to change image. */
    public static final int CHANGE_FREQ = 12;

    /**
     * Initialize this instance.
     * 
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     * @param killWorth the value of killing this invader
     * @param level the current level in the game
     * @param game the game being played
     */
    public Invader(int x, int y, int killWorth, int level, Game game) {
        super(x, y, game, "invader");
        this.killWorth = killWorth;

        width = WIDTH;
        height = HEIGHT;

        moveDirection = MOVE_RIGHT;
        changeDirectionTick = -1; // no change in direction scheduled
        verticalMoveDistance = MIN_VERTICAL_MOVE_DIST + level * VERTICAL_SPEEDUP_PER_LEVEL;
    }

    /**
     * At each clock tick, decide whether to fire, and every changeFreq ticks move and change image.
     */
    protected void update() {
        float randomNum = RandomNumberGenerator.getInstance().getFloat();
        if (randomNum <= FIRE_PROBABILITY) {
            int missileX = x + (width - Missile.WIDTH) / 2;
            int missileY = y + height;
            game.addMissile(new Missile(missileX, missileY, game));
        }

        int currentTick = game.getTicks();
        if (currentTick == changeDirectionTick) {
            moveDirection = -moveDirection;
            y = y + verticalMoveDistance;
        }

        if (currentTick % CHANGE_FREQ == 0) {
            x = x + (moveDirection * HORIZONTAL_MOVE_DIST);
            moveToNextImage();

            if (currentTick > changeDirectionTick) {
                // if next move would hit a side, switch direction and advance next move
                if (moveDirection == Invader.MOVE_RIGHT
                        && (x + width + HORIZONTAL_MOVE_DIST) > game.getWidth()) {
                    changeDirectionTick = currentTick + CHANGE_FREQ;
                } else if (moveDirection == Invader.MOVE_LEFT && x - HORIZONTAL_MOVE_DIST < 0) {
                    changeDirectionTick = currentTick + CHANGE_FREQ;
                }
            }
        }
    }

    /**
     * If collide with another object, then die.
     * 
     * @param other the object hit in the collision @ precond other instanceof Laser
     */
    protected void collide(GameObject other) {
        if (!(other instanceof Laser))
            throw new RuntimeException("Only a laser can collide with an invader.");

        isDead = true;
        game.getPlayer().increaseScore(killWorth);
    }
}
