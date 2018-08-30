package model;

/**
 * The model of a block that can withstand a certain number of hits before it is destroyed.
 */
public class Block extends GameObject {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;

    /**
     * The number of times the block that must be hit before the block disappears.
     */
    private static final int BLOCK_STRENGTH = 4;

    /** The current number of times that the block has been hit. */
    int numHits;

    /**
     * Initialize the block.
     * 
     * @param x the x-coordinate of the position of the block
     * @param y the y-coordinate of the position of the block
     * @param game the game in which the block occurs
     * @param imageNamesKey the name for the images of the block
     */
    public Block(int x, int y, Game game, String imageNamesKey) {
        super(x, y, game, imageNamesKey);

        height = HEIGHT;
        width = WIDTH;
        numHits = 0;
    }

    /**
     * No action to take at each clock tick.
     */
    protected void update() {}

    /**
     * If a missile hits the block, change the image to show partial disintegration. When the number
     * of hits reaches the value numHits, the block disappears.
     * 
     * @param other the object that collided with the block
     */
    protected void collide(GameObject other) {
        if ((other instanceof Missile) || (other instanceof Laser)) {
            numHits = numHits + 1;
            if (numHits == BLOCK_STRENGTH) {
                isDead = true;
            } else {
                moveToNextImage();
            }
        }
    }
}
