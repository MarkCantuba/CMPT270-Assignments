// Mark Cantuba
// MJC8682
// 11214496

package commands;

import containers.patientAccess;
import entities.Patient;


/**
 * creates a new instance of patient, and add it into the ward database.
 * @author Mark
 */
public class newPatientCmd extends CommandStatus{
	// accessor for the singleton object.
	
	/**
	 * adds a patient to the database. the patient object is added into the hashMap 
	 * containing the Singleton of patient dictionary.
	 * @param: name Patient's name
	 * @param: hNum Patient's healthNumber
	 * @throws: throws a runtime exception if 
	 */
	public void addPatient(String name, int hNum) {
		
		Patient p = new Patient(name, hNum);
		if (patientAccess.dict().containsKey(hNum) || patientAccess.dict().containsValue(p)) {
			successful = false;
			throw new RuntimeException("Patient " + name + " with health #" + hNum + " is already in the database!");
		}
		successful = true;
		patientAccess.dict().put(hNum, p);
	}
	
	// testing new patient command
	public static void main(String[] args) {
		System.out.println("***Testing newPatientCmd***");
		newPatientCmd npc = new newPatientCmd();
		
		if (npc.wasSuccessful()) {
			System.out.println("Nothing should have been done yet!");
		}
		npc.addPatient("Mark", 1121);
		if (npc.wasSuccessful() && !patientAccess.dict().containsKey(1121)) {
			System.out.println("The successful variable in command status should now be set to true, and "
					+ "patient Mark with hNum 1121 should now be added to the database");
		}
		npc.addPatient("Derek", 4242);
		if (!npc.wasSuccessful() || !patientAccess.dict().containsKey(4242) || !patientAccess.dict().containsKey(1121)) {
			System.out.println("The successful variable in command status should now be set to true, and "
					+ "patient Derek with hNum 4242 should now be added to the database");
		}
	}
}

