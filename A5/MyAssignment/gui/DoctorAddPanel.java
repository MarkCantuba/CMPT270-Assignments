// Mark Cantuba
// 11214496
// MJC862

package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import commands.AddDoctorCommand;

/**
 * Panel for creating a doctor
 * @author Mark
 */
public class DoctorAddPanel extends JPanel {

	ValueEntryPanel drNamePanel; // panel for dr's name entry
	ValueEntryPanel isSurgeon; // yes or no field to determine if dr is a surgeon
	JTextArea error = null; // error text message
	
	public DoctorAddPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());
		
		// Prompt Label
		JLabel prompt = new JLabel("Please Fill Required Information for Doctor ");
		prompt.setSize(prompt.getPreferredSize());
		add(prompt);
		prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		// Text Field for the doctor's name
		drNamePanel = new ValueEntryPanel("Name");
		drNamePanel.setSize(drNamePanel.getPreferredSize());
		add(drNamePanel);
		drNamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		// Yes or no Text Field for Doctor
		isSurgeon = new ValueEntryPanel("Is Doctor a Surgeon? (Yes/No) ");
		isSurgeon.setSize(isSurgeon.getPreferredSize());
		add(isSurgeon);
		isSurgeon.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		
		// submit Button
		JButton submit = new JButton("Submit");
		submit.setSize(submit.getPreferredSize());
		add(submit);
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		submit.addActionListener(new submitListener());
		add(Box.createVerticalGlue());
	}
	
	/**
	 * Listener for the submit button. Ensures the the entered fields are 
	 * valid.
	 * @author Mark
	 */
	public class submitListener implements ActionListener {

		// Adds a doctor to the system if  submit is pressed.  Also checks if dr isSurgeon
		public void actionPerformed(ActionEvent arg0) {
			if (error != null) {
				remove(error);
				error = null;
			}
			String drName = drNamePanel.getValueAsString();
			String surgeon = isSurgeon.getValueAsString().toLowerCase();
			boolean isSpecial = false;
			
			if (surgeon.equals("yes") || surgeon.equals("YES")) {
				isSpecial = true;
			}
			else {
				isSpecial = false;
			}
			
			AddDoctorCommand addDoctor = new AddDoctorCommand();
			addDoctor.addDoctor(drName, isSpecial);
			if (addDoctor.wasSuccessful()) {
				getTopLevelAncestor().setVisible(false);
			}
			else {
				error = new JTextArea(SplitString.at(addDoctor.getErrorMessage(), 40));
				error.setMaximumSize(error.getPreferredSize());
				add(error);
				error.setAlignmentX(Component.CENTER_ALIGNMENT);
				add(Box.createVerticalGlue());
				revalidate();
			}
			
		}
			
	}
	
	private static final long serialVersionUID = 1;
}
