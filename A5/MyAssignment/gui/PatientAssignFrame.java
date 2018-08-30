// Mark Cantuba
// 11214496
// MJC862

package gui;

import javax.swing.JFrame;

import entities.Doctor;

/**
 * Frame for the assign patient window
 * @author Mark
 *
 */
public class PatientAssignFrame extends JFrame {
	/** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 400;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;
    
	/**
     * Create the frame to display the information for a patient.
     * 
     * @param healthNum the health number of the patient
     * @precond healthNum is the health number of a patient
     */
    public PatientAssignFrame(Doctor dr) {
    	setTitle("Assign a Doctor to Patient");
    	setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    	PatientAssignPanel panel = new PatientAssignPanel(dr);
    	add(panel);
    }

    public static final long serialVersionUID = 1;
}
