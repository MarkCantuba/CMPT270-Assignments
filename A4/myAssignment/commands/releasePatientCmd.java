// Mark Cantuba
// MJC8682
// 11214496

package commands;

import containers.WardAccess;
import containers.patientAccess;


/**
 * This class is a command responsible for releasing a patient from a bed
 * @author Mark
 */
public class releasePatientCmd extends CommandStatus {
	
	/**
	 * releases a patient out of the bed it is assigned in.
	 * @param bedNumber: bed bed label number
	 */
	public void releasePatient(int bedNumber) {
		if (!WardAccess.ward().isOccupied(bedNumber)) {
			successful = false;
			throw new RuntimeException("Given bed is already empty!");
		}
		else if (!WardAccess.ward().isValidLabel(bedNumber)) {
			successful = false;
			throw new RuntimeException("Invalid bed number!");
		}
		successful = true;
		WardAccess.ward().freeBed(bedNumber);
	}
	
	// testing release patient command
	public static void main(String[] args) {
		System.out.println("***Testing release patient command***");
		releasePatientCmd rpc = new releasePatientCmd();
		assignPatientToBedCmd apc = new assignPatientToBedCmd();
		newPatientCmd npc = new newPatientCmd();
		WardAccess.initialize("Potato", 200, 250);

		npc.addPatient("Paul", 1);
		npc.addPatient("Bob", 2);
		npc.addPatient("Steve", 3);
		
		for (int i=1; i < 4; i++) {
			if (!patientAccess.dict().containsKey(i)) {
				System.out.println("Patient " + patientAccess.dict().get(i).getName() + " Should now be in the database!");
			}
		}
		
		apc.assignToBed(1, 200);
		apc.assignToBed(2, 230);
		apc.assignToBed(3, 250);
		
		if (!WardAccess.ward().isOccupied(200) || !WardAccess.ward().isOccupied(230) || !WardAccess.ward().isOccupied(250)) {
			System.out.println("Patient Paul, Bob and Steve should now be assigned to a bed!");
		}
		
		rpc.releasePatient(200);
		rpc.releasePatient(230);
		rpc.releasePatient(250);
		
		if (WardAccess.ward().isOccupied(200) || WardAccess.ward().isOccupied(230) || WardAccess.ward().isOccupied(250)) {
			System.out.println("Patients inside 200, 230 and 250 should now be empty!");
		}
		
		if (WardAccess.ward().availableBeds().size() != 51) {
			System.out.println("The bed ward should now be empty!");
		}
	}
}
