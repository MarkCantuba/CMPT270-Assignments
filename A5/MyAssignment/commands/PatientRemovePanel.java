package commands;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import commands.DropAssocCommand;
import entities.Doctor;
import gui.ValueEntryPanel;

/**
 * Panel for Dropping association between doctor and patient.
 * Looks similar to assign Patient frame
 * @author Mark
 */
public class PatientRemovePanel extends JPanel {

	
	
	ValueEntryPanel textField;
	
	/**
	 * This panel is for assigning a patient to a doctor. Prompts the user for 
	 * a HealthNumber
	 * @param dr
	 */
	public PatientRemovePanel(Doctor dr) {

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
					DropAssocCommand assign = null;
					
					assign = new DropAssocCommand();
					assign.dropAssociation(dr.getName(), hNumber);;
		
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
		
		private static final long serialVersionUID = 1;
		
	}