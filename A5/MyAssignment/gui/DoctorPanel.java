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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entities.Surgeon;
import entities.Doctor;

/**
 * Doctor Panel for prompting the user for a Doctor's name, along 
 * with a prompt if the doctor is a surgeon or not
 * @author Mark
 *
 */
public class DoctorPanel extends JPanel {
		String isSurgeon; // is the Patient a Surgeon?
		JTextField textField; // textField for Name annd is Surgeon
		
		DoctorPanel(Doctor dr) {
		
		/**
		 * Displays information regarding the doctor
		 */
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(new JLabel("Doctor's Name:   " + dr.getName()));
		if (dr instanceof Surgeon) {
			isSurgeon = "YES";
		}
		else {
			isSurgeon = "NO";
		}
		add(new JLabel("Is a Surgeon?:    " + isSurgeon));

		// Button for Assigning a Patient to a doctor
		JButton assignPatient = new JButton("Assign Patient to Doctor");
		assignPatient.setSize(assignPatient.getPreferredSize());
		add(assignPatient);
		assignPatient.setAlignmentX(Component.LEFT_ALIGNMENT);
		assignPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PatientAssignFrame frame = new PatientAssignFrame(dr);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		add(Box.createVerticalGlue());
		
		/**
		 * Prompts the user for a health number. It checks if the given Hnumber is assigned
		 * to the current doctor.
		 */
		JLabel prompt = new JLabel("Access my Patient");
		add(prompt);
		textField = new JTextField(10);
		add(textField);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int number = 0;
				try {
					number = Integer.parseInt(textField.getText());
				}
				catch (NumberFormatException e) {
					JTextArea error = new JTextArea("ENTER AN INTEGER!");
					error.setMaximumSize(error.getPreferredSize());
					add(error);
					error.setAlignmentX(Component.CENTER_ALIGNMENT);
					add(Box.createVerticalGlue());
					revalidate();
					return;
				}
				if (dr.hasPatient(number)) {
					PatientFrame patient = new PatientFrame(number);
					patient.setVisible(true);
				}
				else {
					JTextArea error = new JTextArea("Patient Doesn't Exist");
					error.setMaximumSize(error.getPreferredSize());
					add(error);
					error.setAlignmentX(Component.CENTER_ALIGNMENT);
					add(Box.createVerticalGlue());
					revalidate();
				}
				}});
		Box.createVerticalGlue();
		
		// Button for Assigning a Patient to a doctor
		JButton removePatient = new JButton("Unassign Patient");
		removePatient.setSize(assignPatient.getPreferredSize());
		add(removePatient);
		removePatient.setAlignmentX(Component.LEFT_ALIGNMENT);
		removePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				PatientRemoveFrame frame = new PatientRemoveFrame(dr);
				frame.setLocation(300, 300);
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.setVisible(true);
				}
		});
				add(Box.createVerticalGlue());
	}


	public static final long serialVersionUID = 1;

}
