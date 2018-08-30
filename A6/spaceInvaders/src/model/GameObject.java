package model;

import java.awt.Rectangle;
import java.util.List;

import util.PropertiesDiskStorage;

/**
 * An object that appears on the screen while playing the game until it dies.
 */
public abstract class GameObject {
    /** The width of the object. */
    protected int width;
    /** The height of the object. */
    protected int height;

    /** The x-coordinate for the upper left hand corner of the object. */
    protected int x;
    /** The y-coordinate for the upper left hand corner of the object. */
    protected int y;

    /** The object for the whole game. */
    protected Game game;

    /** Is this object already dead? */
    protected boolean isDead;

    /**
     * The list of the names of the images of the object to be used to display the object on the
     * screen.
     */
    protected List<String> imageNames;

    /** The index of the current image. */
    protected int imageIndex;

    /**
     * Initialize this instance, excluding the name of the collection of images.
     * 
     * @param x the initial x position
     * @param y the initial y position
     * @param game the game
     * @param imageNamesKey the key for accessing the file names of the images
     */
    public GameObject(int x, int y, Game game, String imageNamesKey) {
        this.x = x;
        this.y = y;
        this.game = game;
        isDead = false;
        loadImageNames(imageNamesKey);
        imageIndex = 0;
    }

    /**
     * @return the current x position
     */
    public int getX() {
        return x;
    }

    /**
     * @return the current y position
     */
    public int getY() {
        return y;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return am I dead?
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Set the dead state to be consistent with the parameter.
     * 
     * @param dead the new value for dead state
     */
    protected void setDead(boolean dead) {
        this.isDead = dead;
    }

    /**
     * Obtain the names for the images from the game properties.
     * 
     * @param imageNamesKey the string name for the images of this instance
     */
    public void loadImageNames(String imageNamesKey) {
        imageNames = PropertiesDiskStorage.getInstance().getProperties(imageNamesKey);
    }

    /**
     * @return the String name of the current image of this instance
     */
    public String getCurrentImageName() {
        return imageNames.get(imageIndex);
    }

    /**
     * Make the next image the current image.
     */
    public void moveToNextImage() {
        imageIndex = (imageIndex + 1) % imageNames.size();
    }

    /**
     * The abstract method to take appropriate action when the clock ticks.
     */
    protected abstract void update();

    /**
     * The abstract method to handle the collision of this with the other object.
     * 
     * @param other the object that collided with this object
     */
    protected abstract void collide(GameObject other);

    /**
     * @return the rectangle that bounds this object
     */
    public Rectangle getBoundingRectangle() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * @param object another game object
     * @return does this instance intersect with the argument object?
     */
    public boolean intersects(GameObject other) {
        return getBoundingRectangle().intersects(other.getBoundingRectangle());
    }
}
