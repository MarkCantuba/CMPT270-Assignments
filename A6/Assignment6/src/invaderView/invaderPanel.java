/** 
  * Mark Cantuba
  * MJC862
  * 11214496
  */

package invaderView;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import model.GameInfoProvider;
import model.GameObserver;
import util.PropertiesDiskStorage;
import view.GraphicsPanel;

/**
 * Panel for the invaders remaining window.
 * @author Mark
 *
 */
public class invaderPanel extends GraphicsPanel implements GameObserver {

	/**
	 * Contains all the information about the game. ie:
	 * remaining lives, invaders left etc.
	 */
	private GameInfoProvider gameInfo; 

	/** The Component to be in focus when the containing frame is realized. */
    protected Component focusComponent;
    
    /**
     * Initializes invader panel for painting
     * @param gameInfo: Gathers information regarding the game's changes and stuff.
     */
    public invaderPanel(GameInfoProvider gameInfo) {
		this.gameInfo = gameInfo;
		setDoubleBuffered(true);
	}
    
    /**
     * Paints the panel containing invaders remaining and a picture of an invader.
     */
    @Override
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D bufferedGraphics = (Graphics2D) g;
		
		setBackground(Color.BLACK);
		bufferedGraphics.setPaint(Color.GREEN);

		drawInvader(bufferedGraphics);
		bufferedGraphics.drawLine(0, 44, 450, 44);
	}
	
	/**
	 * Draws the invaders, along with the string associated with it, which is a
	 * Count of the remaining invaders
	 * @param graphics: Graphics used for painting
	 */
	private void drawInvader(Graphics2D graphics) {
		int remainingInvader = gameInfo.invaderRemaining();
		drawString(35, 32, "Invaders Remaining: " + remainingInvader, 35, graphics);
		/**
		 * invaderAnim contains the 2 images of the invader with its hands up/down
		 */
		List<String> invaderAnim = PropertiesDiskStorage.getInstance().getProperties("invader");
		/**
		 * Everytime an invader dies, the animation switches
		 */
		drawImage(80,60, 300, 300, invaderAnim.get(remainingInvader % 2), graphics);	
	}
	
	/**
     * Set the Component of this panel that should have the focus when the frame is realized.
     */
    public void setFocusComponent(Component focusComponent) {
        this.focusComponent = focusComponent;
    }
	
    /**
     * Repaints the panel if the game changes. In this case, the remaining number of 
     * invaders will change, and the invader will switch its arms if an invader is killed
     */
	public synchronized void gameChanged() {
		repaint();
	}
	
	/**
     * @return the Component of this panel that should have the focus when the frame is realized.
     */
    public Component getFocusComponent() {
        return focusComponent;
    }
	
	private static final long serialVersionUID = 1;
}
