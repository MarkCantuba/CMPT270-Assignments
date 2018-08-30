package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import commands.AssignBedCommand;
import commands.ReleasePatientCommand;

import containers.WardAccess;
import entities.Ward;
import entities.Patient;

/**
 * The panel to layout out the status of each bed in the ward. If the bed is empty, a field is
 * supplied for the user to admit a patient into the bed. If a bed is occupied, a button is provided
 * to remove the patient from the bed.
 */
public class BedsPanel extends JPanel {
    /**
     * Create the panel to display the status of each bed, and allow the user to change the status.
     */
    public BedsPanel() {
        /*
         * The creation of the panel is placed in another method as it needs to be invoked whenever
         * the occupancy of a bed is changed.
         */
        build();
    }

    /**
     * Fill in the panel to display the status of each bed, and allow the user to change the status.
     */
    public void build() {
        setLayout(new GridLayout(0, 3));
        // add headers to label the columns
        add(new JLabel("   Bed"));
        add(new JLabel("Occupant"));
        add(new JLabel("Insert into field"));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel("/remove button"));

        Ward ward = WardAccess.ward();
        for (int i = ward.getMinBedLabel(); i <= ward.getMaxBedLabel(); i++) {
            int bed = i;
            add(new JLabel("   " + i));
            if (ward.isOccupied(i)) {
                int healthNum = ward.getPatient(i).getHealthNumber();
                bedOccupiedCase(healthNum);
            } else
                bedVacantCase(bed);
            // Add a blank row to improve spacing
            add(new JLabel(""));
            add(new JLabel(""));
            add(new JLabel(""));
        }
    }

    /**
     * Display the health number of the patient in the current bed, and a button that can be used to
     * remove the patient from the bed.
     * 
     * @param healthNum the health number of the patient in the current bed
     */
    private void bedOccupiedCase(final int healthNum) {
        add(new JLabel("" + healthNum));
        JButton removeButton = new JButton("remove");
        removeButton.setMaximumSize(removeButton.getPreferredSize());
        add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ReleasePatientCommand release = new ReleasePatientCommand();
                release.releasePatient(healthNum);
                if (release.wasSuccessful())
                    refreshPanels(healthNum);
                else
                    JOptionPane.showMessageDialog(BedsPanel.this, release.getErrorMessage());
            }
        });
    }

    /**
     * Include a blank label to indicate the bed is vacant and a field to allow the user to enter
     * the health number of a patient to be assigned the bed.
     * 
     * @param bed the bed that can be assigned a patient
     */
    private void bedVacantCase(final int bed) {
        add(new JLabel(""));
        final JTextField textField = new JTextField(10);
        add(textField);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String valueAsString = textField.getText();
                if (valueAsString == null || valueAsString.length() == 0) {
                    textField.setText("Empty field: " + textField.getText());
                    textField.revalidate();
                    return;
                }
                int healthNum = -1;
                try {
                    healthNum = Integer.parseInt(valueAsString);
                } catch (NumberFormatException e) {
                    textField.setText("Not an int: " + textField.getText());
                    textField.revalidate();
                    return;
                }
                AssignBedCommand assignBedCmd = new AssignBedCommand();
                assignBedCmd.assignBed(healthNum, bed);
                if (assignBedCmd.wasSuccessful())
                    refreshPanels(healthNum);
                else {
                    JOptionPane.showMessageDialog(BedsPanel.this, assignBedCmd.getErrorMessage());
                }
            }
        });
    }

    /**
     * The panel is refreshed have the correct fields and buttons. This is necessary when the status
     * of a patient in a bed changes. Also, if the Ward window was created from a PatientPanel, the
     * patient and the BedPanel for this patient are passed in so that they can be updated when the
     * status of the patient in the bed changes. This method does this.
     * 
     * @param healthNum
     */
    private void refreshPanels(int healthNum) {
        // recreate the panel as it has changed
        removeAll();
        build();
        revalidate();
        /*
         * If the frame has a BedPanel is recorded for this patient, update it.
         */
        Patient p = ((WardFrame) getTopLevelAncestor()).patient;
        BedPanel panel = ((WardFrame) getTopLevelAncestor()).panelOfPatient;
        if (p != null && p.getHealthNumber() == healthNum)
            panel.rebuild(p);
    }

    public static final long serialVersionUID = 1;
}
