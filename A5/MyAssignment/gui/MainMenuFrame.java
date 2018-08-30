// Mark Cantuba
// 11214496
// MJC862

package gui;

import javax.swing.JFrame;

/**
 * Main frame for the main menu window
 * @author Mark
 *
 */
public class MainMenuFrame extends JFrame {
	
	// Standard frame width
	public static final int DEFAULT_WIDTH = 350;
	
	// Standard frame height
	public static final int DEFAULT_HEIGHT = 200;
	
	/**
	 * creates window panel for the main menu.
	 */
	public MainMenuFrame() {
		setTitle("Main Menu");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		MainMenuPanel panel = new MainMenuPanel();
		add(panel);
	}
	
	private static final long serialVersionUID = 1;
}
