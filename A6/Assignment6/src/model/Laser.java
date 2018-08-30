package model;

/**
 * A Laser fired by the player.
 */
public class Laser extends GameObject {
    public static final int WIDTH = 3;
    public static final int HEIGHT = 25;

    /** The distance to move when it is time to move. */
    public static int MOVE_DISTANCE = 6;

    /** How frequently (in terms of ticks) the laser is to change image. */
    public static final int CHANGE_FREQ = 4;

    /** A count of the number of ticks since creation. */
    private int tickCount;

    /**
     * Initialize the laser.
     * 
     * @param x the initial x-coordinate for the missile
     * @param y the initial y-coordinate for the missile
     * @param game the game being played
     */
    public Laser(int x, int y, Game game) {
        super(x, y, game, "laser");
        width = WIDTH;
        height = HEIGHT;
        tickCount = 0;
    }

    /**
     * Every clock tick, move MOVE_DISTANCE. Check for reaching the top or colliding with another
     * object. If neither of these happen, change image every changeFreq ticks.
     */
    protected void update() {
        y = y - MOVE_DISTANCE;
        if (y < 0) {
            isDead = true;
        } else {
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
     * Check for a collision with an invader, a block or a missile, and if so, return the other
     * object of the collision. A laser does not hit an explosion, the player, or another laser.
     * 
     * @return the invader, block, or missile that the laser hit, if any
     */
    protected GameObject objectHit() {
        for (Invader invader : game.invadersList)
            if (intersects(invader)) {
                return invader;
            }

        for (Block block : game.blocksList)
            if (intersects(block)) {
                return block;
            }

        for (Missile missile : game.missilesList)
            if (intersects(missile)) {
                return missile;
            }

        return null;
    }

    /**
     * The method to handle the collision of this with the other object.
     * 
     * @param other the object that collided with this object
     */
    protected void collide(GameObject other) {
        isDead = true;
        other.collide(this);
        /* Create an explosion centered at the front/top of the laser. */
        Explosion explosion =
                new Explosion(x + WIDTH / 2 - Explosion.WIDTH / 2, y - Explosion.HEIGHT / 2, game);
        game.addExplosion(explosion);
    }
}
