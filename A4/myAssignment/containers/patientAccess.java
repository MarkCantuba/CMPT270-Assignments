// Mark Cantuba
// MJC8682
// 11214496

package containers;

import java.util.TreeMap;
import entities.Patient;

/**
 * Singleton class for the patient container.
 * @author Mark
 */
public class patientAccess {
	
	/**
	 * this constructor ensures that the class will not be initialized.
	 */
	private patientAccess() {};
	
	/**
	 * Container that contains patients in the Hospital ward, with patien's
	 * Health number as key, and the Patient object as value.
	 */
	private static TreeMap<Integer, Patient> dict;
	
	/**
	 * Method that initializes the container
	 */
	public static void initialize() {
		dict = new TreeMap<Integer, Patient>();
	}
	/**
	 * gets the dictionary containing patients in the database.
	 * initializes the dictionary if it is set to null.
	 * @return a dictionary of patients.
	 */
	public static TreeMap<Integer, Patient> dict() {
		if (dict == null) {
			initialize();	
		}
		return dict;
	}
	
	// testing patient access
	public static void main(String[] args) {
		/**
		 * Testing patient Access class.
		 */
		System.out.println("***Testing patientAccess() class***");
	
		if (dict != null) {
			System.out.println("This method has not yet been initialized! Check either constructor or init method!");
		}
		
		if (dict() == null) {
			System.out.println("The pA object should now be initialized!");
		}
		
	}
	
	
}
