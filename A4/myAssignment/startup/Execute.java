package startup;

import userInterfaces.ConsoleIO;
import userInterfaces.DialogIO;

/**
 * Executes the whole hospital system!
 * @author Mark
 */
public class Execute {
	public static void main(String[] args) {
		ConsoleIO cio = new ConsoleIO();
		DialogIO dio = new DialogIO();
		HospitalSystem hs = new HospitalSystem();
		
		int ioID = hs.chooseIOstyle();
		hs.createWard();
		hs.run();
		
		if (ioID == 1) {
			cio.outputString("***Current system state***");
			cio.outputString(hs.toString());
		}
		else {
			dio.outputString("***Current system state***\n"
							+  hs.toString());
		}
	}
}
