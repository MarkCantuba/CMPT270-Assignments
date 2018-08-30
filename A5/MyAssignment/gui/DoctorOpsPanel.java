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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import containers.DoctorSetAccess;

/**
 * Panel for the Doctor's Operation. consist of a button for adding a doctor,
 * a button for obtaining all the doctors in the database, and an exit button.
 * @author Mark
 *
 */
public class DoctorOpsPanel extends JPanel {

	public DoctorOpsPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createVerticalGlue());
		/**
		 * Button for adding a new doctor
		 */
		JButton addDoctor = new JButton("Add Doctor");
		addDoctor.setSize(addDoctor.getPreferredSize());
		add(addDoctor);
		addDoctor.setAlignmentX(Component.CENTER_ALIGNMENT);
		addDoctor.addActionListener(new addDoctorListener());
		add(Box.createVerticalGlue());
		
		DoctorAccessPanel drAccess = new DoctorAccessPanel();
		add(drAccess);
		add(Box.createVerticalGlue());
		
		/**
		 * Button to get a list of Doctors that are currently in the system.
		 */
		JButton getAllDoctors = new JButton("Display All Doctors");
		getAllDoctors.setSize(getAllDoctors.getPreferredSize());
		add(getAllDoctors);
		getAllDoctors.setAlignmentX(Component.CENTER_ALIGNMENT);
		getAllDoctors.addActionListener(new getDoctorsListener());
		add(Box.createVerticalGlue());
		
		
		// exit button. This hides instead of terminating the window 
		JButton exit = new JButton("Exit");
		exit.setSize(exit.getPreferredSize());
		add(exit);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				exit.getTopLevelAncestor().setVisible(false);
			}
		});
		add(Box.createVerticalGlue());
	}
	
	/**
	 * Add doctor Listener. initializes add doctor command if button
	 * is pressed through a window.
	 * @author Mark
	 */
	public class addDoctorListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			DoctorAddFrame frame = new DoctorAddFrame();
			frame.setLocation(300,300);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
	}

	/**
	 * Listener for getDoctors button. Initializes the getDoctors window
	 * if button is pressed
	 * @author Mark
	 */
	public class getDoctorsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, DoctorSetAccess.dictionary().values());
			
		}
	}
	
	
	
	public static void main(String[] args) {
		DoctorOpsFrame frame = new DoctorOpsFrame();
		frame.setVisible(true);
	}
	
	private static final long serialVersionUID = 1;
}
