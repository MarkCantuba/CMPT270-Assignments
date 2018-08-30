// Mark Cantuba
// 11214496
// MJC862
package gui;

import javax.swing.JFrame;

import containers.DoctorSetAccess;
import entities.Doctor;


public class DoctorFrame extends JFrame {
	 /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 200;

    /**
     * Create the frame to display the information for a patient.
     * 
     * @param healthNum the health number of the patient
     * @precond healthNum is the health number of a patient
     */
    public DoctorFrame(String drName) {
        Doctor dr = DoctorSetAccess.dictionary().get(drName);
        if (dr != null) {
            setTitle("Dr. " + dr.getName() + "'s Database");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            DoctorPanel panel = new DoctorPanel(dr);
            add(panel);
        } else
            throw new RuntimeException("Name not Found!");
    }

    public static final long serialVersionUID = 1;
}
