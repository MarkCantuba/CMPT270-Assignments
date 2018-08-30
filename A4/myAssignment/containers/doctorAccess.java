// Mark Cantuba
// MJC8682
// 11214496

package containers;

import java.util.TreeMap;
import entities.Doctor;

/**
 * Singleton class for container containing doctors in 
 * the Hospital ward system
 * @author Mark
 */
public class doctorAccess {
	
	/**
	 * this private method makes sure that this class does not initialize!
	 */
	private doctorAccess() {}
	
	/** a hashmap containing the doctors in the system, with their names as key
	 * and the Doctor object as its value.
	 */
	private static TreeMap<String, Doctor> dict;
	
	/**
	 * initializes the 
	 */
	public static void initialize() {
		dict = new TreeMap<String, Doctor>();
	}
	/**
	 * returns the dictionary, dict, containing records of the doctors in the Hospital
	 * Ward. Name of the doctor is the key, and the doctor object is the value.
	 * initializes the dictionary if still not so.
	 * @return dictionary containing doctors.
	 */
	public static TreeMap<String, Doctor> dict() {
		if (dict == null) {
			initialize();
		}
		return dict;
	}
	
	// testing doctor access
	public static void main(String[] args) {
		System.out.println("***Testing doctorAccess Class***");
		if (dict != null) {
			System.out.println("the dictionary in doctorAccess shouldn't have been initialized yet!");
		}
		
		if (dict() == null) {
			System.out.println("The dictionary should now have been initialized after init() method!");
		}
	}
}

