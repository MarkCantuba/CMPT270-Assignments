// Mark Cantuba
// 11214496
// MJC862

package gui;

import javax.swing.JFrame;

/**
 * Creates a window for adding a doctor
 * @author Mark
 *
 */
public class DoctorAddFrame extends JFrame {
	/** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 250;

    /**
     * Create the frame to add a doctor.
     */
    public DoctorAddFrame() {
        setTitle("Add a Doctor");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        DoctorAddPanel panel = new DoctorAddPanel();
        add(panel);
    }

    public static final long serialVersionUID = 1;
}
	
