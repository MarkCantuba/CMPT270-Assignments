package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.List;

import model.GameInfoProvider;
import model.GameObserver;
import model.GameObject;

/**
 * The panel to show the current game scene. It is an observer of the game model in order to be
 * notified of changes, and it accesses information from the game via the GameInfoProvider
 * interface.
 */
public class GamePanel extends GraphicsPanel implements GameObserver {
    public static final long serialVersionUID = 1;

    /**
     * The object that provides information about the game.
     */
    private GameInfoProvider gameInfo;

    /**
     * The list of the objects in the game for a specific painting of the panel.
     */
    private List<GameObject> objects;

    /**
     * Initialize the game panel, including setting it up for double buffering.
     * 
     * @param listener the listener for key strokes
     * @param gameInfo the source for information about the game
     */
    public GamePanel(KeyListener listener, GameInfoProvider gameInfo) {
        addKeyListener(listener);
        this.gameInfo = gameInfo;
        setDoubleBuffered(true);
        gameInfo.setInvaderSynchronizationObject(this);
        objects = gameInfo.getGameObjects();
    }

    /**
     * When the game changes, repaint the view.
     */
    public synchronized void gameChanged() {
        objects = gameInfo.getGameObjects();
        repaint();
    }

    /**
     * Paint the game scene on the panel: the background and sprites. If the game is paused or is
     * over, include a message to that effect.
     * 
     * @param g the graphics to use to paint the view
     */
    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D bufferedGraphics = (Graphics2D) g;
        bufferedGraphics.setPaint(Color.BLACK);
        bufferedGraphics.fillRect(0, 0, getWidth(), getHeight());

        for (GameObject object : objects)
            drawImage(object.getX(), object.getY(), object.getWidth(), object.getHeight(),
                    object.getCurrentImageName(), bufferedGraphics);

        bufferedGraphics.setPaint(Color.GREEN);
        if (gameInfo.isOver())
            drawString(getWidth() / 2 - 200, getHeight() / 2, "Game Over",
                    GraphicsPanel.LARGE_FONT_SIZE, bufferedGraphics);
        else if (gameInfo.isPaused())
            drawString(getWidth() / 2 - 200, getHeight() / 2, "Paused",
                    GraphicsPanel.LARGE_FONT_SIZE, bufferedGraphics);
    }
}
