// Mark Cantuba
// 11214496
// MJC862
package gui;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainMenuPanel class initializes a window that contains the choices
 * for the main menu.
 * @author Mark
 *
 */
public class MainMenuPanel extends JPanel {

	/**
	 * initializes the window containing buttons. The window consist
	 * of 4 different buttons. The buttons are the following:
	 * 			- PatientOps -> executes the patient's operation window
	 * 			- DoctorOps -> executes doctor operation window
	 * 			- WardInfo -> displays information about the ward
	 * 			- Exit -> terminates the program.
	 */
	public MainMenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(Box.createHorizontalGlue());
		
		JButton PatientOps = new JButton("Patient Operations");
		PatientOps.setSize(PatientOps.getPreferredSize());
		add(PatientOps);
		PatientOps.setAlignmentX(CENTER_ALIGNMENT);
		PatientOps.addActionListener(new PatientOpsListener());
		add(Box.createVerticalGlue());
		
		JButton DoctorOps = new JButton("Doctor Operations");
		DoctorOps.setSize(DoctorOps.getPreferredSize());
		add(DoctorOps);
		DoctorOps.setAlignmentX(CENTER_ALIGNMENT);
		DoctorOps.addActionListener(new DoctorOpsListener());
		add(Box.createVerticalGlue());
		
		JButton WardInfo = new JButton("Display Ward Information");
		WardInfo.setSize(getPreferredSize());
		add(WardInfo);
		WardInfo.setAlignmentX(CENTER_ALIGNMENT);
		WardInfo.addActionListener(new WardInfoListener());
		add(Box.createVerticalGlue());
		
		
		JButton exit = new JButton("Exit");
		exit.setSize(exit.getPreferredSize());
		add(exit);
		exit.setAlignmentX(CENTER_ALIGNMENT);
		exit.addActionListener(new exitListener());
		add(Box.createVerticalGlue());	
	}
	
	/**
	 * Action listener for Patient Operations button. 
	 * When button is pressed, it will initialize the patient Ops window
	 * @author Mark
	 */
	public class PatientOpsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			PatientOpsFrame frame = new PatientOpsFrame();
			frame.setLocation(300,300);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
	
	/**
	 * Listener for Doctorops button. When pressed, it will 
	 * execute the Doctor ops window
	 * @author Mark
	 */
	public class DoctorOpsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			DoctorOpsFrame frame = new DoctorOpsFrame();
			frame.setLocation(300,300);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
	
	/**
	 * Listenr for WardInfo Button. Displays ward state when the
	 * button is pressed
	 * @author Mark
	 */
	public class WardInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			WardFrame frame = new WardFrame();
			frame.setLocation(300,300);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
	
	/**
	 * Listener for the exit button. Shuts down the system
	 * with return status of 0 for success.
	 * @author Mark
	 *
	 */
	public class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
	
	private static final long serialVersionUID = 1;
}
