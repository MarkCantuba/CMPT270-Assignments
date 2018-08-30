// Mark Cantuba
// MJC8682
// 11214496

package commands;

import containers.WardAccess;

/**
 * This class simply gives the user a listing of all available beds.
 * @author Mark
 */
public class getEmptyBedsCmd extends CommandStatus{
	
	/**
	 * This method gives a listing of all available beds!
	 * @return string containing available beds
	 */
	public String getEmptyBeds() {
		String holder = "***Listing of Available Beds***\n";
		for (int i = 0; i < WardAccess.ward().availableBeds().size() ; i++) {
			holder += WardAccess.ward().availableBeds().get(i) + "\n";
		}
		successful = true;
		return holder;
	}
	
	// testing commands for get empty beds
	public static void main(String[] args) {
		System.out.println("***Displaying List of Empty Beds***\n");
		
		WardAccess.initialize("Potato", 200, 250);
		
		getEmptyBedsCmd gebc = new getEmptyBedsCmd();
		
		assignPatientToBedCmd aptbc = new assignPatientToBedCmd();
		
		System.out.println(gebc.getEmptyBeds());
		
		newPatientCmd npc = new newPatientCmd();
		
		npc.addPatient("Bob", 1);
		npc.addPatient("Steve", 2);
		
		aptbc.assignToBed(1, 200);
		aptbc.assignToBed(2, 230);
		
		// 200 and 230 should not be in the printed list now!
		System.out.println(gebc.getEmptyBeds());
	}
}
