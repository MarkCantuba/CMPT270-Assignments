package model;

/**
 * The interface to control the Game, and information needed to control the Game.
 */
public interface GameControl {
    public void start();

    public void togglePaused();

    public Player getPlayer();
}
