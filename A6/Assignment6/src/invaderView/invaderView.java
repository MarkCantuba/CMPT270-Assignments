/** Mark Cantuba 
  * 11214496
  * MJC862   
  */

package invaderView;

import java.awt.Component;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.GameInfoProvider;

/**
 * Frame for the additional invader panel, containing the number of invaders left
 * @author Mark
 */
public class invaderView extends JFrame {
	
	
	
    /**
     * Initialize the frame for the various views to be inserted into it, where width and height are
     * the dimensions for the game.
     */
    public invaderView(int width, int height) {
        setTitle("SLAY THE INVADERS!!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Width and height passed in are those for the game panel, so add enough height for the
         * title bar and the information panel, and enough width for the border.
         */
        setSize(width, height);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
    }
    
    /**
     * Set the frame visible showing the panel passed in, and set the focus in the Component
     * obtained from the panel.
     * Obtained from View Class
     * @param panel the panel to be shown in the view
     */
    public void displayPanel(invaderPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().validate();
        Component focusComponent = panel.getFocusComponent();
        if (focusComponent != null)
            focusComponent.requestFocusInWindow();
        setVisible(true);
    }
    
    /**
     * Creates the frame for the invader View. It is designed so that the window
     * will appear on the left of the window
     * @param gameInfoProvider
     */
    public void showInvaderView(GameInfoProvider gameInfoProvider) {
    	invaderPanel panel = new invaderPanel(gameInfoProvider);
    	gameInfoProvider.addObserver(panel);
    	panel.setFocusComponent(panel);
    	this.setLocation(800,0);
    	displayPanel(panel);
    }
    
    private static final long serialVersionUID = 1;
}
