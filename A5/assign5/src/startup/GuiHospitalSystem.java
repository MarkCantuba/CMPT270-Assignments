package startup;

import gui.CreateWardFrame;

import javax.swing.JFrame;

/**
 * A simple hospital system with only one ward. Patients and doctors can be created, and patients
 * assigned to a doctor and/or placed in a bed of the ward. Input and output is done by a graphical
 * user interface.
 */
public class GuiHospitalSystem {
    /**
     * Start up the hospital application.
     */
    public static void main(String[] args) {
        CreateWardFrame frame = new CreateWardFrame();
        frame.setLocation(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
