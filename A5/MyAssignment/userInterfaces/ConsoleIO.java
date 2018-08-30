package userInterfaces;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class with the input and output methods to read a String, read an int, output a message, and
 * display a list of choices from which the user must select the index of a choice. The input and
 * output is done via the console.
 */
public class ConsoleIO implements InputOutputInterface {
    /**
     * The Scanner used to scanner the characters entered at the console.
     */
    private Scanner consoleIn = new Scanner(System.in);

    /**
     * Display a prompt and read the string entered.
     * 
     * @param prompt the string to be displayed as a prompt
     * @return the next line of input from the console
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        return consoleIn.nextLine();
    }

    /**
     * Display a prompt and read the int entered.
     * 
     * @param prompt the string to be displayed as a prompt
     * @return the int obtained from the console
     */
    public int readInt(String prompt) {
        int result = 0; // must be initialized
        boolean successful;
        do {
            successful = true;
            try {
                System.out.print(prompt);
                result = consoleIn.nextInt();
            } catch (InputMismatchException e) {
                successful = false;
                String faultyInput = consoleIn.nextLine();
                System.out.print("You entered \"" + faultyInput + "\" which is not an int."
                        + "\nPlease try again: \n");
            }
        } while (!successful);
        consoleIn.nextLine(); // discard the remainder of the line
        return result;
    }

    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     * 
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */
    public int readChoice(String[] options) {
        String prompt = "\nPlease select an option ";
        for (int i = 0; i < options.length; i++) {
            prompt = prompt + "\n" + i + ": " + options[i];
        }
        prompt = prompt + "\nEnter the number of your selection: ";
        int result = readInt(prompt);
        if (result < 0 || result >= options.length) {
            outputString("You entered " + result + " that is not between 0 and "
                    + (options.length - 1) + "\nPlease try again.  ");
            return readChoice(options);
        } else
            return result;
    }

    /**
     * Output the String parameter to the screen.
     * 
     * @param outString the string whose value is to be displayed
     */
    public void outputString(String outString) {
        System.out.print(outString);
    }

    /**
     * Simple tests of the classes.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        InputOutputInterface ioHandler = new ConsoleIO();
        ioHandler.outputString("This will test the input and output methods.\n");
        String result = ioHandler.readString("Enter a string: ");
        ioHandler.outputString("The string read was " + result + "\n");
        int i = ioHandler.readInt("Enter an integer value: ");
        ioHandler.outputString("The integer read was " + i + "\n");
        String[] options = {"First", "Second", "Third"};
        int intOption = ioHandler.readChoice(options);
        ioHandler.outputString("The option selected was " + options[intOption] + "\n");
    }
}
