package model;

/**
 * A Missile that can be fired by an invader.
 */
public class Missile extends GameObject {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 25;

    /** The distance to move when it is time to move. */
    public static int MOVE_DISTANCE = 6;

    /** How frequently (in terms of ticks) the missile is to change. */
    public static final int CHANGE_FREQ = 4;

    /** A count of the number of ticks since creation. */
    private int tickCount;

    /**
     * Initialize the missile.
     * 
     * @param x the initial x-coordinate for the missile
     * @param y the initial y-coordinate for the missile
     * @param game the game being played
     */
    public Missile(int x, int y, Game game) {
        super(x, y, game, "invader_missile");

        width = WIDTH;
        height = HEIGHT;
        tickCount = 0;
    }

    /**
     * Every clock tick, move MOVE_DISTANCE. Check for reaching the bottom or colliding with another
     * object. If neither of these happen, change image every changeFreq ticks.
     */
    protected void update() {
        y = y + MOVE_DISTANCE;
        if (y + height > game.getHeight())
            isDead = true;
        else {
            GameObject other = objectHit();
            if (other != null) {
                collide(other);
            } else {
                tickCount = tickCount + 1;
                if (tickCount % CHANGE_FREQ == 0) {
                    moveToNextImage();
                }
            }
        }
    }

    /**
     * Check for a collision with the player or a block, and if so, return the other object of the
     * collision. Missiles do not hit invaders, explosions or other missiles. A laser detects a
     * missile hit.
     * 
     * @return the player or block that the missile hit
     */
    protected GameObject objectHit() {
        if (intersects(game.player)) {
            return game.player;
        }

        for (Block block : game.blocksList)
            if (intersects(block)) {
                return block;
            }

        return null;
    }

    /**
     * The method to handle the collision of this with the other object.
     * 
     * @param other the object that collided with this object
     */
    protected void collide(GameObject other) {
        if (other instanceof Laser)
            isDead = true; // the laser found the collision and creates the explosion
        else {
            isDead = true;
            other.collide(this);
            /* Create an explosion centered at the front/bottom of the missile. */
            Explosion explosion =
                    new Explosion(x + WIDTH / 2 - Explosion.WIDTH / 2, y + HEIGHT
                            - Explosion.HEIGHT / 2, game);
            game.addExplosion(explosion);
        }
    }

}
