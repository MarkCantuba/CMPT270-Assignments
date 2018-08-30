// Mark Cantuba
// 11214496
// MJC862

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import commands.AssignDoctorCommand;
import entities.Doctor;

/**
 * Panel for patient assign panel. will consist of a text field for a patient's
 * hNumber.
 * @author Mark
 */
public class PatientAssignPanel extends JPanel {
	
	ValueEntryPanel textField;
	
	/**
	 * This panel is for assigning a patient to a doctor. Prompts the user for 
	 * a HealthNumber
	 * @param dr
	 */
	public PatientAssignPanel(Doctor dr) {

		/**
		 * This chunk if for the text field for the prompt for a health NUmber
		 */
		textField = new ValueEntryPanel("Enter the Patient's Health Number");
		textField.setSize(textField.getPreferredSize());
		add(textField);
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		/**
		 * Submit Button
		 */
		JButton submit = new JButton("Submit");
		submit.setSize(submit.getPreferredSize());
		add(submit);
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int hNumber = 0;
				if (textField.getValueAsString() != "" || textField.getValueAsString() != null) {
					try {
						hNumber = textField.getValueAsInt();
					} catch (NumberFormatException e) {
						JTextArea error = new JTextArea("ENTER AN INTEGER!");
						error.setMaximumSize(error.getPreferredSize());
						add(error);
						error.setAlignmentX(Component.CENTER_ALIGNMENT);
						add(Box.createVerticalGlue());
						revalidate();
						return;
					}
					AssignDoctorCommand assign = null;
					
					assign = new AssignDoctorCommand();
					assign.assignDoctor(dr.getName(), hNumber);
		
					if (assign.wasSuccessful()) {
						getTopLevelAncestor().setVisible(false);
					}
					else {
						JTextArea error = new JTextArea(assign.getErrorMessage());
						error.setMaximumSize(error.getPreferredSize());
						add(error);
						error.setAlignmentX(Component.CENTER_ALIGNMENT);
						add(Box.createVerticalGlue());
						revalidate();
					}
				}	
			}});
		
	}
	
	public static final long serialVersionUID = 1;
}
