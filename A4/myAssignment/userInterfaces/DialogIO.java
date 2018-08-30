package userInterfaces;

import javax.swing.JOptionPane;

/**
 * This class extends the abstract class AbstractDialogIO.
 * This is the JPane implimentation of the console io.
 * @author Mark
 *
 */
public class DialogIO extends AbstractDialogIO {
	
	/**
	 * Dialog box version of readString method
	 */
	@Override
	public String readString(String prompt) {
		String dialogIn = JOptionPane.showInputDialog(null, prompt);
        String userIn;
        if (dialogIn != null && dialogIn.length() > 0) {
            try {
                userIn = dialogIn;
            } catch (NumberFormatException e) {
                userIn = readString("Given Value is not a String!\n" + prompt);
            }
        } else
            userIn = readString("Please enter an String value!\n" + prompt);
        return userIn;
	}
	/**
	 * Dialog box version of readInt method
	 */
	@Override
	public int readInt(String prompt) {
		String userIn = JOptionPane.showInputDialog(null, prompt);
        int value;
        if (userIn != null && userIn.length() > 0) {
            try {
                value = Integer.parseInt(userIn);
            } catch (NumberFormatException e) {
                value = readInt("Given Value is not an Integer!\n" + prompt);
            }
        } else
            value = readInt("Please enter an integer value!\n" + prompt);
        return value;
	}

	/**
	 * Dialog box version of outputString method
	 */
	@Override
	public void outputString(String outString) {
		JOptionPane.showMessageDialog(null, outString);
		
	}
}
