package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import commands.CreateWardCommand;

/**
 * The panel to obtain input to initialize the ward, and then it will start the main system.
 */
public class CreateWardPanel extends JPanel {
    /**
     * The panel for the entry of the ward's name.
     */
    ValueEntryPanel namePanel;

    /**
     * The panel for the entry of the label of the first bed of the ward.
     */
    ValueEntryPanel firstLabelPanel;

    /**
     * The panel for the entry of the label of the last bed of the ward.
     */
    ValueEntryPanel lastLabelPanel;

    /**
     * A panel for an error message, if an error should occur.
     */
    JTextArea error = null;

    /**
     * Create a panel with a field for the entry of the ward's name, a field for the label of the
     * first bed, a field for the label of the last bed, and a submit button to submit the data for
     * the creation of the ward.
     */
    public CreateWardPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        JLabel prompt = new JLabel("Enter the ward information");
        prompt.setMaximumSize(prompt.getPreferredSize());
        add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        namePanel = new ValueEntryPanel("name");
        namePanel.setMaximumSize(namePanel.getPreferredSize());
        add(namePanel);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        firstLabelPanel = new ValueEntryPanel("first bed label");
        firstLabelPanel.setMaximumSize(firstLabelPanel.getPreferredSize());
        add(firstLabelPanel);
        firstLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());

        lastLabelPanel = new ValueEntryPanel("last bed label");
        lastLabelPanel.setMaximumSize(lastLabelPanel.getPreferredSize());
        add(lastLabelPanel);
        lastLabelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());


        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(submitButton.getPreferredSize());
        add(submitButton);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new SubmitListener());
        add(Box.createVerticalGlue());
    }

    /**
     * The listener for the press of the submit button. When it happens, obtain the data values from
     * the fields, and create the ward. Afterwards, create and make visible the window with the main
     * options for the user.
     */
    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (error != null) {
                remove(error); // remove error from previous submission
                error = null;
            }
            String name = namePanel.getValueAsString();
            int firstBedNum = -1;
            int lastBedNum = -1;
            try {
                firstBedNum = firstLabelPanel.getValueAsInt();
                lastBedNum = lastLabelPanel.getValueAsInt();
            } catch (NumberFormatException e) {
                revalidate(); // getValueAsInt modified the text field, so redraw
                return;
            }
            CreateWardCommand createWard = new CreateWardCommand();
            createWard.createWard(name, firstBedNum, lastBedNum);
            if (createWard.wasSuccessful()) {
                getTopLevelAncestor().setVisible(false);
                PatientOpsFrame frame = new PatientOpsFrame(); // Used for assign5 */
                frame.setLocation(300, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } else {
                /*
                 * Divide the error message into lines short enough to fit in the window, and store
                 * the message in the error text area.
                 */
                error = new JTextArea(SplitString.at(createWard.getErrorMessage(), 50));
                error.setMaximumSize(error.getPreferredSize());
                add(error);
                error.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(Box.createVerticalGlue());
                revalidate();
            }
        }
    }

    public static final long serialVersionUID = 1;
}
