package model;

/**
 * Model of an explosion as a subtype of GameObject.
 */
public class Explosion extends GameObject {
    public static final int WIDTH = 34;
    public static final int HEIGHT = 34;

    /** The number of ticks until the explosion disappears. */
    public static final int MAX_TICKS = 4;

    /** How frequently (in terms of ticks) the explosion is to change. */
    public static final int CHANGE_FREQ = 2;

    /** A count of the number of ticks since creation. */
    private int tickCount;

    /**
     * Initialize the explosion instance.
     */
    public Explosion(int x, int y, Game game) {
        super(x, y, game, "explosion");

        width = WIDTH;
        height = HEIGHT;
        tickCount = 0;
    }

    /**
     * Do nothing when the explosion collides with another object.
     */
    public void collide(GameObject other) {}

    /**
     * React to the tick of the clock: change image every changeFreq ticks, and die when tick count
     * reaches MAX_TICKS.
     */
    protected void update() {
        if (tickCount == MAX_TICKS) {
            isDead = true;
        } else {
            tickCount = tickCount + 1;
            if (tickCount % CHANGE_FREQ == 0) {
                moveToNextImage();
            }
        }
    }
}
