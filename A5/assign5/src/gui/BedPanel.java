package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import commands.ReleasePatientCommand;

import entities.Patient;

/**
 * A panel to display the bed status for a patient. If the patient is in a bed, a button is provided
 * to remove the patient from the bed. If the patient is not in a bed, a button is provided to open
 * the ward window. From within the ward window, any patient can be assigned a bed.
 */
public class BedPanel extends JPanel {
    /**
     * Construct the bedPanel for the bed associated with this patient.
     * 
     * @param patient the patient whose bed association is to be displayed
     */
    public BedPanel(Patient patient) {
        rebuild(patient);
    }

    /**
     * Build the bedPanel for the bed associated with this patient. To accommodate the panel being
     * changed, remove previous contents before adding the new content and revalidate the panel.
     * 
     * @param patient the patient whose bed association is to be displayed
     */
    protected void rebuild(final Patient patient) {
        removeAll();
        final int bed = patient.getBedLabel();
        if (bed != -1) {
            add(new JLabel("Bed: " + bed));
            JButton removeButton = new JButton("remove");
            add(removeButton);
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    ReleasePatientCommand release = new ReleasePatientCommand();
                    release.releasePatient(patient.getHealthNumber());
                    if (release.wasSuccessful())
                        rebuild(patient);
                    else
                        JOptionPane.showMessageDialog(BedPanel.this, release.getErrorMessage());
                }
            });
        } else {
            add(new JLabel("Bed: " + "none"));
            JButton assignButton = new JButton("assign");
            add(assignButton);
            assignButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    WardFrame frame = new WardFrame(patient, BedPanel.this);
                    frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    frame.setLocation(300, 300);
                    frame.setVisible(true);
                }
            });
        }
        revalidate();
    }

    public static final long serialVersionUID = 1;
}
