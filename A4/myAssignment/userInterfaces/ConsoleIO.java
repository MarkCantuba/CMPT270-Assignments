package userInterfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class implements the IO interface, and is the console implementation
 * of the whole Hospital System
 * @author Mark
 *
 */
public class ConsoleIO implements InputOutputInterface {
	Scanner sc = new Scanner(System.in);

	@Override
	public String readString(String prompt) {
		System.out.printf(prompt);
		String consoleIn = sc.nextLine();
        String userIn;
        if (consoleIn != null && consoleIn.length() > 0) {
            try {
                userIn = consoleIn;
            } catch (NumberFormatException e) {
                userIn = readString("Given Value is not a String!\n" + prompt);
            }
        } 
        else {
            userIn = readString("");
        }
        return userIn;
	}
	
	@Override
	public int readInt(String prompt) {
		int response = 0;
		boolean valid = false;
		while (!valid) {
			System.out.printf(prompt);
			valid = true;
			try {
				response = sc.nextInt();
			}
			catch (InputMismatchException ime) {
				valid = false;
				String error = sc.nextLine();
				System.out.println("The given input " + error + " is invalid! It is not a Integer!");
				
			}
		}
		return response;
	}

	@Override
	public int readChoice(String[] options) {
		System.out.println("***Please select from the following commands***");
		for (int i=0 ; i < options.length; i++) {
			System.out.println(options[i]);
		}
		int choice = readInt("Please enter the command ID: ");
		
		if (choice >= 0 && choice <= options.length) {
			return choice;
		}
		else {
			System.out.println("Invalid Choice!");
			return readChoice(options);
		}
	}

	@Override
	public void outputString(String outString) {
		System.out.println(outString);
	}
}
