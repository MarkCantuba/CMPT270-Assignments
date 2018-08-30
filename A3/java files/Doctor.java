// Mark Cantuba
// MJC862
// 11214496
import java.util.LinkedList;

/**
 * Doctor class is a descendant of the basic doctor class
 * the doctor class contains a Linked list containing the assigned 
 * patients to the doctor. 
 */
public class Doctor extends BasicDoctor {

	protected LinkedList<Patient> patientList; // a Linked List of patients assigned to this doctor.
	
	/**
	 * constructor class that creates an instance of the Doctor object
	 * @param name: name of the doctor.
	 */
	public Doctor(String name) {
		super(name);
		patientList = new LinkedList<Patient>();
	}
	
	/**
	 * this method assigns a patient to this doctor
	 * @param patientObj: patient object created by Patient class
	 */
	public void assignPatient(Patient patientObj) {
		patientList.add(patientObj);
	}
	
	/**
	 * unssign a patient out of the doctor's patient list. 
	 * Will print a message out if the patient doesn't exist!
	 * @param patientObj
	 */
	public void unassignPatient(Patient patientObj) {
		if (patientList.contains(patientObj)) {
			patientList.remove(patientObj);
		}
		else {
			System.out.println("Patient is not assigned to this doctor.");
		}
	}
	
	/**
	 * displays the list of patients the doctor is assigned to
	 * @return: list of patients
	 */
	public LinkedList<Patient> getPatients() {
		return patientList;
	}
	
	/**
	 * toString method that displays all the information regarding the doctor class into
	 * a printable string format. Overrides toString method of BasicDoctor class.
	 */
	@Override
	public String toString() {
		String patient = "";
		for (Patient x: patientList) {
			patient += x.getName() + "\n";
		}
		return "Doctor's Name: " + super.getName() +
				"\n\n**Assigned Patients to Doctor " + super.getName() + "**\n" + patient;
	}
	
	public static void main(String[] args) {
		Doctor test1 = new Doctor("Strange");
		Doctor test2 = new Doctor("Mort");
		Doctor test3 = new Doctor("Hartman");
		
		Patient p1 = new Patient("Stewie", 2010011);
		Patient p2 = new Patient("Zidane", 7777777);
		Patient p3 = new Patient("Squall", 2910100);
		
		System.out.println("**Testing Doctor class**");
		
		// Testing assignPatient() and unssignPatient method
		test1.assignPatient(p1);
		test1.assignPatient(p2);
		
		test2.assignPatient(p2);
		test2.assignPatient(p3);
		
		test3.assignPatient(p1);
		test3.assignPatient(p3);
		
		if (!test1.getPatients().contains(p1) || !test1.getPatients().contains(p2)) {
			System.out.println("Patient p1 and p2 should be assigned to Doctor Strange. Check .assignPatient Method");
		}
		if (!test2.getPatients().contains(p2) || !test2.getPatients().contains(p3)) {
			System.out.println("Patient p2 and p3 should be assigned to Doctor Mort. Check .assignPatient Method");
		}
		if (!test3.getPatients().contains(p1) || !test3.getPatients().contains(p3)) {
			System.out.println("Patient p1 and p3 should be assigned to Doctor Hartman. Check .assignPatient Method");
		}
		
		test1.unassignPatient(p1);
		test1.unassignPatient(p2);
		
		test2.unassignPatient(p3);
		
		if (test1.getPatients().contains(p1) || test1.getPatients().contains(p2)) {
			System.out.println("test1 should not containt p1 and p2 anymore due to remove patients method. Check removePatient() method.");
		}
		if (test2.getPatients().contains(p3)) {
			System.out.println("test2 should not containt p3 anymore due to remove patients method. Check removePatient() method.");
		}
		
		if (!test3.getPatients().contains(p1) || !test3.getPatients().contains(p3)) {
			System.out.println("test3 should still have p1 and p2 in its assigned patient list. Check previous removal calls.");
		}
		
		// Testing getPatients() method
		
		LinkedList<Patient> expected1 = new LinkedList<Patient>();
		LinkedList<Patient> expected2 = new LinkedList<Patient>();
		LinkedList<Patient> expected3 = new LinkedList<Patient>();
		expected2.add(p2);
		
		expected3.add(p1);
		expected3.add(p3);
		
		if (!expected1.equals(test1.getPatients()) || !expected2.equals(test2.getPatients()) 
				|| !expected3.equals(test3.getPatients())) {
			System.out.println("The expected list obtained from test1 2 and 3 didn't match expected. Check get List method!");
		}
		
		// Displaying Doctor toString method!
		System.out.println("\n****************************************");
		System.out.println("**Displaying Doctor's toString Method**\n");
		System.out.println("-test1-\n" + test1.toString());
		System.out.println("-test2-\n" + test2.toString());
		System.out.println("-test3-\n" + test3.toString());
		System.out.println("******************************************");
		
		
		
		

	}

}
