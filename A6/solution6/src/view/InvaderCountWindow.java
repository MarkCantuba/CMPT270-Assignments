package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import model.GameInfoProvider;
import model.GameObserver;
import util.PropertiesDiskStorage;

/**
 * A window to show the invader image and a count of the number of invaders remaining.
 */
public class InvaderCountWindow extends JFrame {
    /**
     * Initialize the frame, add the panel, and set the frame visible.
     * 
     * @param gameInfo the interface to access the invader count
     */
    public InvaderCountWindow(GameInfoProvider gameInfo) {
        setTitle("Invader Count");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(250, 175);
        add(new InvaderCountPanel(gameInfo));
        setVisible(true);
    }

    /**
     * The panel to show the invader image and a count of the number of invaders remaining.
     */
    private class InvaderCountPanel extends JPanel implements GameObserver {
        /**
         * The interface to access the invader count.
         */
        private GameInfoProvider gameInfo;

        /**
         * The number of invaders when the game was last checked.
         */
        private int count;

        /**
         * A list of the images of an invader.
         */
        private List<BufferedImage> images;

        /**
         * An iterator for the list of images.
         */
        private Iterator<BufferedImage> imagesIter;

        /**
         * Initialize the fields of the panel ready for the first painting.
         * 
         * @param gameInfo the interface to access the invader count
         */
        public InvaderCountPanel(GameInfoProvider gameInfo) {
            this.gameInfo = gameInfo;
            count = gameInfo.getInvaderCount();
            gameInfo.addObserver(this); // this instance must be notified when the game changes

            List<String> imageNames = PropertiesDiskStorage.getInstance().getProperties("invader");
            images = new LinkedList<BufferedImage>();
            for (String name : imageNames)
                images.add(ImageCache.getInstance().getImage(name));
            imagesIter = images.iterator();
        }


        /**
         * The action to perform whenever the game changes.
         */
        public void gameChanged() {
            int newCount = gameInfo.getInvaderCount();
            if (newCount != count) {
                count = newCount;
                repaint();
            }
        }

        /**
         * Paint the new contents of the panel.
         * 
         * @param g the Graphics to be used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setPaint(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());

            if (!imagesIter.hasNext())
                imagesIter = images.iterator();
            BufferedImage image = imagesIter.next();;
            g2.drawImage(image, 100, 25, 50, 50, null);

            Paint oldColor = g2.getPaint();
            g2.setPaint(Color.GREEN);
            g2.drawString("The number of invaders is " + count, 20, 110);
            g2.setPaint(oldColor);
        }

        public static final long serialVersionUID = 1;
    }

    public static final long serialVersionUID = 1;
}
