// Mark Cantuba
// 11214496
// MJC862

package commands;

import containers.WardAccess;
import containers.patientAccess;

/**
 * a class that assigns a patient to a bed in the hospital ward.
 * @author Mark
 */
public class assignPatientToBedCmd extends CommandStatus {
	/**
	 * this class assigns a patient to a bed, via his/her health number
	 * @param hNum: patient's health number
	 * @param bedNumber: bed number you wish to assign he patient into.
	 */
	public void assignToBed(int hNum, int bedNumber) {
		if (!patientAccess.dict().containsKey(hNum)) {
			successful = false;
			throw new RuntimeException("The given Health Number is not in the database!!");
		}
		else if (patientAccess.dict().isEmpty()) {
			successful = false;
			throw new RuntimeException("Someone is assigned to this bed!");
		}
		else if (!WardAccess.ward().isValidLabel(bedNumber)) {
			throw new RuntimeException("Invalid bed Label!");
		}
		successful = true;
		WardAccess.ward().assignPatientToBed(patientAccess.dict().get(hNum), bedNumber);
	}
	
	// test commands for assign patient to bed
	public static void main(String[] args) {
		System.out.println("***Testing assignPatienttoBedCmd***");
		
		newPatientCmd npc = new newPatientCmd();
		npc.addPatient("Paul", 1);
		npc.addPatient("Bob", 2);
		if (!patientAccess.dict().containsKey(1) || !patientAccess.dict().containsKey(2)) {
			System.out.println("Paul and Bob should now be added to the database. Re-check patientAccess class!");
		}
		WardAccess.initialize("Potato", 200, 250);
		assignPatientToBedCmd aptb = new assignPatientToBedCmd();
		aptb.assignToBed(1, 200);
		
		if (!WardAccess.ward().isOccupied(200) || !aptb.successful) {
			System.out.println("Bed 200 should now be occupied by patient Paul, whose hNumber is 1!");
		}
		
		aptb.assignToBed(2, 220);
		if (!WardAccess.ward().isOccupied(220) || !aptb.successful) {
			System.out.println("Bed 200 should now be occupied by patient Paul, whose hNumber is 1!");
		}
	}
}
