package gui;

import javax.swing.JFrame;

import containers.WardAccess;
import entities.Ward;
import entities.Patient;

/**
 * The frame for the window to display the ward information, and allow the user to change the
 * occupancy of beds.
 */
public class WardFrame extends JFrame {
    /** The standard width for the frame. */
    public static final int DEFAULT_WIDTH = 350;

    /** The standard height for the frame. */
    public static final int DEFAULT_HEIGHT = 1000;

    /**
     * Create the frame for the information on the ward and ward operations.
     */
    public WardFrame() {
        Ward ward = WardAccess.ward();
        setTitle(ward.getName() + " Ward");
        int height;
        height =
                Math.min(DEFAULT_HEIGHT,
                        30 + 50 * (ward.getMaxBedLabel() - ward.getMinBedLabel() + 1));
        setSize(DEFAULT_WIDTH, height);
        WardPanel panel = new WardPanel();
        add(panel);
    }

    /**
     * When the creation of the ward window is invoked from a PatientPanel, the patient is passed in
     * and recorded in this field.
     */
    protected Patient patient;

    /**
     * When the creation of the ward window is invoked from a PatientPanel, the BedPanel displaying
     * the bed information of the patient is passed in and recorded in this field so that the panel
     * can be updated if the bed occupancy of the patient changes.
     */
    protected BedPanel panelOfPatient;

    /**
     * Create the frame for the information on the ward and ward operations. When the creation of
     * the ward window is invoked from a PatientPanel, the patient and BedPanel displaying the bed
     * information of the patient are passed in and recorded so that the panel can be updated if the
     * bed occupancy of the patient changes.
     */
    public WardFrame(Patient patient, BedPanel panel) {
        this();
        this.patient = patient;
        panelOfPatient = panel;
    }

    public static final long serialVersionUID = 1;
}
