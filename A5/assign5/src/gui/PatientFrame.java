package gui;

import javax.swing.JFrame;

import containers.PatientSetAccess;
import entities.Patient;

/**
 * The frame for the window to display the information for a patient, and accept operations on the
 * patient.
 */
public class PatientFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 400;

    /**
     * Create the frame to display the information for a patient.
     * 
     * @param healthNum the health number of the patient
     * @precond healthNum is the health number of a patient
     */
    public PatientFrame(int healthNum) {
        Patient patient = PatientSetAccess.dictionary().get(healthNum);
        if (patient != null) {
            setTitle(patient.getName() + " (" + healthNum + ")");
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            PatientPanel panel = new PatientPanel(patient);
            add(panel);
        } else
            throw new RuntimeException("Invalid health number " + healthNum);
    }

    public static final long serialVersionUID = 1;
}
