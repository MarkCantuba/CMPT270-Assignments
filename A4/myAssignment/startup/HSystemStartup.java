package startup;

import containers.WardAccess;
import containers.doctorAccess;
import containers.patientAccess;

/**
 * This class is responsible for initializing the 3 singleton methods.
 * @author Mark
 */
public class HSystemStartup {
	
	/**
	 * Ensures that this class will not initialize.
	 */
	private HSystemStartup() {};
	
	/**
	 * Initializes all 3 singleton containers!
	 * @param Name: Name of the ward
	 * @param minLabel: minimum bed label in the ward
	 * @param maxLabel: maximum bed label in the ward
	 */
	public static void initializeMethod(String Name, int minLabel, int maxLabel) {
		WardAccess.initialize(Name, minLabel, maxLabel);
		patientAccess.initialize();
		doctorAccess.initialize();
	}
}
