package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A panel with a prompt label and a text field for the entry of a data value. The data value can be
 * accessed as a String or an int value. If accessed as an int value, but it is invalid, the field
 * value has "Error: " added at its front, and a NumberFormatException exception is thrown.
 */
public class ValueEntryPanel extends JPanel {
    /**
     * The field for the entry of the data value.
     */
    private JTextField textField;

    /**
     * The constructor to create the label and text field.
     * 
     * @param prompt the prompt to appear beside the text field
     */
    public ValueEntryPanel(String prompt) {
        JLabel promptLabel = new JLabel(prompt);
        add(promptLabel);

        textField = new JTextField(15);
        add(textField);
    }

    /**
     * Return the data entered as a String.
     * 
     * @return the data entered into the text field
     */
    public String getValueAsString() {
        return textField.getText();
    }

    /**
     * Return the data entered as an int.
     * 
     * @precond an integer value has been entered into the text field
     * @return the integer entered into the text field
     */
    public int getValueAsInt() {
        String valueAsString = textField.getText();
        int value = -1;
        if (valueAsString != null && valueAsString.length() > 0) {
            try {
                value = Integer.parseInt(valueAsString);
            } catch (NumberFormatException e) {
                textField.setText("Not an int: " + textField.getText());
                throw e;
            }
        } else {
            textField.setText("Empty field: " + textField.getText());
            throw new NumberFormatException("Didn't enter a value for the int");
        }
        return value;
    }

    public static final long serialVersionUID = 1;
}
