package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The panel to display the ward information and allow the user to change the occupancy of beds.
 */
public class WardPanel extends JPanel {
    /**
     * Create the panel to display the ward information and allow the user to change the occupancy
     * of beds.
     */
    public WardPanel() {
        setLayout(new BorderLayout());

        BedsPanel bedsPanel = new BedsPanel();
        add(bedsPanel, BorderLayout.CENTER);

        JPanel pageEndPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        pageEndPanel.add(exitButton);
        add(pageEndPanel, BorderLayout.PAGE_END);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                getTopLevelAncestor().setVisible(false);
            }
        });
    }

    public static final long serialVersionUID = 1;
}
