package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.*;
import util.PropertiesDiskStorage;

/**
 * A panel across the top that displays the score, the level, and the number of remaining lives of
 * the user. It is a GameModelObserver, so method gameChanged() is invoked whenever the game status
 * changes.
 */
public class GameInfoPanel extends GraphicsPanel implements GameObserver {
    public static final long serialVersionUID = 1;

    public static final int LIVES_HEIGHT = 10;
    public static final int HEIGHT = (LIVES_HEIGHT * 4) + 5;

    /**
     * The object that provides information about the game.
     */
    private GameInfoProvider gameInfo;

    /**
     * Initialize the panel ready for painting.
     * 
     * @param gameInfo the interface to the game used to obtain information from the game
     */
    public GameInfoPanel(GameInfoProvider gameInfo) {
        this.gameInfo = gameInfo;
        setDoubleBuffered(true);
    }

    /**
     * When the game changes, repaint the panel to show the latest values.
     */
    public synchronized void gameChanged() {
        repaint();
    }

    public static final int INFO_Y_OFFSET = 20;
    public static final int SCORE_LEFT_OFFSET = 10;
    public static final int WIDTH_OF_LEVEL_FIELD = 70;

    /**
     * Paint the panel to show the score, level, and remaining lives.
     * 
     * @param g the graphics used for painting
     */
    @Override
    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D bufferedGraphics = (Graphics2D) g;

        setBackground(Color.BLACK);
        bufferedGraphics.setPaint(Color.GREEN);
        drawString(SCORE_LEFT_OFFSET, INFO_Y_OFFSET, "Score: " + gameInfo.getPlayerScore(),
                GraphicsPanel.REG_FONT_SIZE, bufferedGraphics);
        drawString((getWidth() - WIDTH_OF_LEVEL_FIELD) / 2, INFO_Y_OFFSET,
                "Level: " + gameInfo.getLevel(), GraphicsPanel.REG_FONT_SIZE, bufferedGraphics);
        drawLives(gameInfo.getPlayerLives(), bufferedGraphics);

        bufferedGraphics.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public static final int LIVES_RIGHT_OFFSET = 150;
    public static final int LIVES_TEXT_LENGTH = 55;
    public static final int LIVES_PER_ROW = 5;
    public static final int LIVES_WIDTH = 18;

    /**
     * In the upper lefthand corner, give the label Lives, and draw an icon for each life recorded
     * in lives. Draw them in rows with LIVES_PER_ROW icons per row.
     * 
     * @param lives the number of icons to show for the players lives remaining
     * @param graphics the graphics used for painting
     */
    private void drawLives(int lives, Graphics2D graphics) {
        drawString(getWidth() - LIVES_RIGHT_OFFSET, INFO_Y_OFFSET, "Lives: ",
                GraphicsPanel.REG_FONT_SIZE, graphics);

        String imageName = PropertiesDiskStorage.getInstance().getProperty("lives_indicator");
        int xOffset = getWidth() - LIVES_RIGHT_OFFSET + LIVES_TEXT_LENGTH;
        int yOffset = 0;

        int col = 0;
        for (int i = 0; i < lives; i++) {
            drawImage(xOffset + (col * LIVES_WIDTH), yOffset, LIVES_WIDTH, LIVES_HEIGHT, imageName,
                    graphics);
            col++;

            if (col % LIVES_PER_ROW == 0) {
                col = 0;
                yOffset = yOffset + LIVES_HEIGHT;
            }
        }
    }
}
