// Mark Cantuba
// 11214496
// MJC862

package gui;

import javax.swing.JFrame;

/**
 * Default window frame for the doctor operations
 * @author Mark
 *
 */
public class DoctorOpsFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;
    
    /**
     * initializes the frame for the window of doctor operations
     */
    public DoctorOpsFrame() {
    	setTitle("Doctor Menu");
    	setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    	DoctorOpsPanel panel = new DoctorOpsPanel();
    	add(panel);
    	
    }
    
    public static final long serialVersionUID = 1;
}
