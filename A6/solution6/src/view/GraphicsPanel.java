package view;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * A panel with the method drawImage to draw an image in the panel where the image is obtained from
 * an image cache by name, and the method drawString to display text in the panel.
 */
public class GraphicsPanel extends JPanel {
    public static final long serialVersionUID = 1;

    public static final int REG_FONT_SIZE = 18;
    public static final int LARGE_FONT_SIZE = 72;
    public static final String FONT_TYPE = "Arial";

    /**
     * Draw the image with the specified imageName at the location specified.
     * 
     * @param x the x-coordinate of the location for the image
     * @param y the y-coordinate of the location for the image
     * @param width the width of the area to be used for the image
     * @param height the height of the area to be used for the image
     * @param imageName the name of the image in the image cache
     * @param graphics the graphics to use to do the painting
     * @precond ImageCache.getInstance().getImage(imageName) != null
     */
    protected void drawImage(int x, int y, int width, int height, String imageName,
            Graphics2D graphics) {
        BufferedImage image = ImageCache.getInstance().getImage(imageName);

        if (image != null)
            graphics.drawImage(image, x, y, width, height, null);
        else
            throw new IllegalStateException("Failed to obtain the BufferedImage "
                    + "corresponding to name " + imageName);
    }

    /**
     * Draw theText at the location specified, using the font size specified.
     * 
     * @param x the x-coordinate of the location for the text
     * @param y the y-coordinate of the location for the text
     * @param theText the text to be drawn
     * @param fontSize the size for the font to be used
     * @param graphics the graphics to use to do the painting
     */
    protected void drawString(int x, int y, String theText, int fontSize, Graphics2D graphics) {
        Font oldFont = graphics.getFont();
        Font font = new Font(FONT_TYPE, Font.BOLD, fontSize);
        graphics.setFont(font);
        graphics.drawString(theText, x, y);
        graphics.setFont(oldFont);
    }
}
