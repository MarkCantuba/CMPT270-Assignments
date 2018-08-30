// Mark Cantuba
// 11214496
// MJC862

package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * a JTextField to access specific doctors
 * @author Mark
 *
 */
public class DoctorAccessPanel extends JPanel {

	JTextField drName;
	
	public DoctorAccessPanel() {
		JLabel name = new JLabel("Access Doctor");
		add(name);
		drName = new JTextField(10);
		add(drName);
		drName.addActionListener(new drNameListener());
	}
	
	public class drNameListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String docName = drName.getText();
			DoctorFrame frame = null;
			try {
				frame = new DoctorFrame(docName);
			}
			catch (RuntimeException r) {
				drName.setText(r.getMessage());
				drName.revalidate();
				return;
			}
			frame.setLocation(300, 300);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setVisible(true);
		}
		
	}
	
	private static final long serialVersionUID = 1;

}
