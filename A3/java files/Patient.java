// Mark Cantuba
// MJC862
// 11214496
import java.util.LinkedList;

/**
 * The Patient class adds up to the original Person class. It has
 * methods consisting of a LinkedList containing a set of Doctors
 * that are assigned to a Patient, and an int instance that contains
 * the bedLabel number that the patient is assigned to.
 */
public class Patient extends Person {

	private int patientBed; // instance that stores the bedLabel the patient is assigned to.
	
	private LinkedList<Doctor> assignedDoctors; // a linked list containing patient's assigned doctors.
	
	/**
	 * Constructor method initializes Patient Object, and super Person.
	 * @param pName: patient's Name
	 * @param hNumber: patient's Health Number
	 */
	public Patient(String pName, int hNumber) {
		super(pName, hNumber);
		patientBed = -1;
		assignedDoctors = new LinkedList<Doctor>();
	}
	
	/**
	 * getter method to get the bed label the patient is assigned to
	 * -1 if the patient is not assigned to a bed. Defaults to -1
	 * @return: the bed label the patient is stored in
	 */
	public int getBedLabel() {
		return patientBed;	
	}
	
	/**
	 * This method assigns a doctor to a patient, and is added to the linked list.
	 * @param drInfo: doctor object, containing doctor info.
	 */
	public void assignDoctor(Doctor drInfo) {
		assignedDoctors.add(drInfo);
	}
	
	/**
	 * sets the integer bed label on which the patient is stored in, when
	 * assigned to a bed.
	 * @param bedNumber: the bed Number the patient will be assigned to.
	 */
	public void setBed(int bedNumber) {
		patientBed = bedNumber;
	}
	
	/**
	 * Returns the list in which the Doctors are stored in. They are the 
	 * assigned doctors to the patient.
	 * @return Linked list containing the doctors assigned to the patient.
	 */
	public LinkedList<Doctor> getDoctorList() {
		return assignedDoctors;
	}
	
	/**
	 * Unsigns the doctor that is assigned to this patient, and removes it out of the Linked List
	 * @param drInfo: doctor object, that is to be removed.
	 */
	public void unassignDoctor(Doctor drInfo) {
		if (assignedDoctors.contains(drInfo)) {
			assignedDoctors.remove(drInfo);
		}
		else {
			System.out.println("Doctor you want to unsign is not assigned to this patient!");
		}
	}
	
	/**
	 * toString method overrides the ones for the Person class, and displays the doctors assigned
	 * to the patient, and the bedLabel the patient is stored in
	 */
	@Override
	public String toString() {
		String doctors = "";
		String bedLabel = Integer.toString(getBedLabel());
	
		for (Doctor x: getDoctorList()) {
			doctors += x.getName() + "\n";
		}
		if (getBedLabel() == -1) {
			return "Patient Name: " + super.getName() + " | Health Number: " + super.getHealthNumber() +
					"\n\n**Doctors assigned to patient " + super.getName() + "**\n" + doctors + 
					"\n**Patient is not assigned to a bed!**\n";
		}
		return "Patient Name: " + super.getName() + " | Health Number: " + super.getHealthNumber() +
		"\n\n**Doctors assigned to this patient**\n" + doctors +
				"\nPatient is assigned to bed " + bedLabel + "\n";	
	}
	
	public static void main(String[] args) {
		System.out.println("**Testing Patient Class**");
		
		Patient test1 = new Patient("Cole", 9210011); 
		Patient test2 = new Patient("Steve", 9412911);
		
		// Testing some Person methods in Patient subclass!
		if (!test1.getName().equals("Cole") || !test2.getName().equals("Steve")) {
			System.out.println("the getName method from Person class isn't compatible with its subclass. Modify getName() method!");
		}
		if (test1.getHealthNumber() != 9210011 || test2.getHealthNumber() != 9412911) {
			System.out.println("the getHealthNumber method from Person class isn't compatible with its subclass. Modify getHealthNumber() method!");
		}
		
		// Testing Patient methods!
		
		// case 1: testing getBedLabel() method
		if (test1.getBedLabel() != -1 || test2.getBedLabel() != -1) {
			System.out.println("The default bed label should be -1. because the person is not assigned to a bed."
					+ " Fix constructor class!");
		}	
		test1.setBed(290);
		test2.setBed(210);
		if (test1.getBedLabel() != 290 || test2.getBedLabel() != 210) {
			System.out.println("The bed label has been changed to test1 = 290 and test2 = 210. Check setBed() method.");
		}
		
		// case 2: testing assignDoctor and unssign doctor methods
		Doctor dr1 = new Doctor("Strange");
		Doctor dr2 = new Doctor("Malfurion");
		Doctor dr3 = new Doctor("Mick");
		Doctor dr4 = new Doctor ("Sabre");
		test1.assignDoctor(dr1);
		test1.assignDoctor(dr2);
		test1.assignDoctor(dr3);
		test1.assignDoctor(dr4);
		
		test2.assignDoctor(dr2);
		test2.assignDoctor(dr4);
		
		
		if (!test1.getDoctorList().get(0).getName().equals("Strange") && !test1.getDoctorList().get(1).getName().equals("Malfurion")
				&& !test1.getDoctorList().get(2).getName().equals("Mick") && !test1.getDoctorList().get(3).getName().equals("Sabre")) {
			System.out.println("The names above should be added to the Linked list of patient Cole. Double check assign Doctor method");
		}
		
		if (!test1.getDoctorList().get(1).getName().equals("Malfurion") && !test1.getDoctorList().get(3).getName().equals("Sabre")) {
			System.out.println("The names above should be added to the Linked list of patient Cole, but got " + test1.getDoctorList() + " instead!");
		}
		
		test1.unassignDoctor(dr2);
		test1.unassignDoctor(dr3);
		
		test2.unassignDoctor(dr2);
		test2.unassignDoctor(dr4);
		
		if (test1.getDoctorList().size() != 2 || test2.getDoctorList().size() != 0) {
			System.out.println("dr2 and dr3 should be now be removed from test1 and there should be no doctors assigned"
					+ " to test 2. Check .unssignDoctor() method!");
		}
	
		// testing getList() method!
		LinkedList<Doctor> expected1 = new LinkedList<Doctor>();
		expected1.add(dr1);
		expected1.add(dr4);
		LinkedList<Doctor> expected2 = new LinkedList<Doctor>();		
		if (!test1.getDoctorList().equals(expected1) || !test2.getDoctorList().equals(expected2)) {
			System.out.println("The output of test1.getList and test2.getlist did not match the expected output. Please check"
					+ " check getList() method.");
		}
		System.out.println("\n******************************************");
		System.out.println("**Displaying Patient's toString Method**\n");
		System.out.println("-test1-\n" + test1.toString());
		System.out.println("\n-test2-\n" + test2.toString());
		System.out.println("******************************************");
	}
}
