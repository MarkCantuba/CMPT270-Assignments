// Mark Cantuba
// MJC862
// 11214496

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Hospital System that represents a single Ward. Prompts the user for commands
 * using Scanner package
 * @author Mark
 *
 */
public class HospitalSystem {
	
	private Ward ward; // a hospital ward.
	private Map<Integer, Patient> patients; // hash map used to store <hNumber,Patient> pairs for patients in the system.
	private Map<String, Doctor> doctors; // hash map used to store <drName, Doctor> pairs for doctors in the system.
	
	/**
	 * Constructor method that creates a new instance of Hospital system method.
	 * @param nameOfWard: the name of the Ward
	 * @param minBedLabel: 1st bed label in the ward
	 * @param maxBedLabel: last bed label in the ward
	 */
	public HospitalSystem(String nameOfWard, int minBedLabel, int maxBedLabel) {
		ward = new Ward(nameOfWard, minBedLabel, maxBedLabel);
		patients = new HashMap<Integer, Patient>();
		doctors = new HashMap<String, Doctor>();
	}
	
	/**
	 * getter method to access patient hashMap
	 * @return hashmap containing patients stored in the system.
	 */
	private Map<Integer, Patient> getPatientHash() {
		return patients;
	}
	
	/**
	 * getter method to access doctors hashMap
	 * @return hashmap containing doctors stored in the system.
	 */
	private Map<String, Doctor> getDoctorHash() {
		return doctors;
	}
	
	/**
	 * getter method to access the ward
	 * @return return the ward instance
	 */
	private Ward getWard() {
		return ward;
	}
	
	/**
	 * Displays all the unoccupied bedds in the ward.
	 */
	public void displayEmptyBeds() {
		System.out.println(ward.getEmptyBeds());
	}
	
	/**
	 * adds a patient into the Ward's database.
	 * prints out a message if the patient is already in the database!
	 * @param patient: patient object containing patient's information!
	 */
	public void addPatient(Patient patient) {
		if (patients.containsKey(patient.getHealthNumber())) {
			System.out.println("Patient " + patient.getName() + " is already in the database!");
		}
		else {
			this.patients.put(patient.getHealthNumber(), patient);	
			System.out.println("Successfully added patient into the database!");
		}
	}
	
	/**
	 * add Doctor simply adds a given Doctor into the ward's database. Prints out a message 
	 * if doctor is already in database
	 * @param doctor: doctor object containing doctor's information
	 */
	public void addDoctor(Doctor doctor) {
			this.doctors.put(doctor.getName(), doctor);
	}
	
	/**
	 * assigns a doctor to a given patient
	 * @param dr: doctor created by Doctor class
	 * @param patient: patient created by the Patient class
	 */
	public void assignDrToPatient(Doctor dr, Patient patient) {
		patient.assignDoctor(dr);
		dr.assignPatient(patient);
		System.out.println("Successfully assigned doctor to patient!");
	}
	
	/**
	 * releases a patient from a bed label!
	 * @param bedLabel: bed label where the patient is stored in.
	 */
	public void releasePatient(int bedLabel) {
		if (!ward.isOccupied(bedLabel)) {
			System.out.println("No one is in this bed!");
		}
		else {
			ward.releasePatient(bedLabel);
			System.out.println("Successfully released patient!");
		}
	}
	
	/**
	 * assigns a patient into an unoccupied bed inside the ward
	 * @param bedNumber: bed label to where to store the patient
	 * @param patient: patient created by patient class
	 * @precondition
	 * 		- bed Number has to be within range, or else will throw out an exception!
	 * 		- the bed has to be unoccupied!
	 */
	public void assignPatientToBed(int bedNumber, Patient patient) {
	
		if (!ward.isValidLabel(bedNumber)) {
			throw new ArrayIndexOutOfBoundsException("bed number provided out of range! Please input a value within range " + 
		ward.getMinBedLabel() + " and " + ward.getMaxBedLabel());
		}
		else {
			patient.setBed(bedNumber);
			ward.assignPatientToBed(patient, bedNumber);
			System.out.println("Successfully assigned patient to bed!");
		}
	}
	
	/**
	 * Unassign a doctor to a patient, and also unassign patient to doctor
	 * @param dr: doctor created from of the doctor class
	 * @param patient: patient created from the patient class
	 */
	public void dropAssociation(Doctor dr, Patient patient) {
		if (!dr.getPatients().contains(patient) && !patient.getDoctorList().contains(dr)) {
			System.out.println("Patient is not assigned to doctor and doctor is not assigned to patient!!");
		}
		else {
			dr.unassignPatient(patient);
			patient.unassignDoctor(dr);
			System.out.println("Successfully dropped Doctor-Patient Association!");
		}
	}
	
	/**
	 * Displays the System, including information regarding the ward, and doctors and patients that are in the database
	 */
	public void displaySystem() {
		
		System.out.println("********************************************************");
		System.out.println("***WARD " + ward.getName().toUpperCase() + "***");
		System.out.println(ward);
		System.out.println("********************************************************");
		System.out.println("**Displaying doctors in the database**");
		int counter1 = 1;
		for (String doctor: getDoctorHash().keySet()) {
			Doctor name = doctors.get(doctor);
			if (name instanceof Surgeon) {
				System.out.println(counter1 + ") " + name);
			}
			else {
				System.out.println(counter1 + ") " + name);
			}
			counter1++;
		}
		
		System.out.println("********************************************************");
		System.out.println("\n**Displaying patients in the database**");
		int counter = 1;
		for (int patient: getPatientHash().keySet()) {
			Patient name = patients.get(patient);
			System.out.println(counter + ")" + name);
			counter++;
		}
		System.out.println("********************************************************");
	}
	
	public static void main(String[] args) {
		HospitalSystem hs = new HospitalSystem("Wennie Hut General", 200, 300);
		
		Doctor dr;
		Patient patient;
		boolean isRunning = true;
		
		// List of commands the user can pick and use.
		while (isRunning) {
			System.out.println("\nPlease pick a number from the following commands: ");
			System.out.println("1 - Quit");
			System.out.println("2 - Add Patient to System");
			System.out.println("3 - Add Doctor to System");
			System.out.println("4 - Assign Doctor to Patient");
			System.out.println("5 - Display Unoccupied Beds");
			System.out.println("6 - Assign Patient to a Bed");
			System.out.println("7 - Release a Patient");
			System.out.println("8 - Drop doctor-patient Association");
			System.out.println("9 - Display Current System State");
			System.out.printf("\nWhat would you like to do: ");
			Scanner sc = new Scanner(System.in);
			
			try {
				int task = sc.nextInt();
				sc.nextLine();	
				
				switch(task) {
				case 1: // Quits the system using hs.quit() method.
					System.out.println("Goodbye!");
					isRunning = false;
					break;
										
				case 2: // Adds a patient to the system.
					System.out.printf("Please enter Patient's name: ");
					String patientName = sc.nextLine();
					
					System.out.printf("Please enter Patient's Health Number: "); // Assuming that the user will enter an integer hNumber!
					int hNumber = sc.nextInt();
					
					patient = new Patient(patientName, hNumber);
					hs.addPatient(patient);
					break;
										
				case 3: // Adds a doctor in the system
					System.out.printf("Please enter Doctor's name: ");
					String drName = sc.nextLine();
					
					System.out.printf("Is the doctor a Surgeon? (y/n): ");
					String choice = sc.nextLine();
					
					if (choice.toLowerCase().equals("y")) {
						dr = new Surgeon(drName);
						hs.addDoctor(dr);
						System.out.println("Surgeon Added to the System Successfully!");
					}
					
					else {
						dr = new Doctor(drName);
						hs.addDoctor(dr);
						System.out.println("Doctor Added to the System Successfully!");
					}
					break;
				
				case 4: // assign doctor to a patient. The doctor and the patient has to be in the system!
					System.out.printf("Please Enter the Doctor's name: ");
					String doctor = sc.nextLine();
					
					System.out.printf("Please Enter the health number of the patient you want to assign the doctor to: ");
					int hNum = sc.nextInt();
					
					if (!hs.getPatientHash().containsKey(hNum) || !hs.getDoctorHash().containsKey(doctor)) {
						System.out.println("Doctor or Patient is not in the database!");
						break;
					}
					else {
						Doctor d = hs.getDoctorHash().get(doctor);
						Patient p = hs.getPatientHash().get(hNum);
						
						d.assignPatient(p);
						p.assignDoctor(d);
						System.out.println("Successfully assigned doctor to a patient!");
						break;
					}	
					
				case 5: // display empty beds
					System.out.println("**List of Empty Beds**");
					hs.displayEmptyBeds();
					break;
										
				case 6: // assign patient to a bed
					System.out.printf("Please specify a bed label you want to assign the patient to: ");
					int bedLabel = sc.nextInt();
					
					System.out.printf("Please enter the health number of the patient you want to store in this bed: "); // Assuming that the user will enter an existing hNumber
					int hn = sc.nextInt();
					
					if (hs.getWard().isOccupied(bedLabel)) {
						System.out.println("This bed is Occupied!");
						break;
					}		
					hs.assignPatientToBed(bedLabel, hs.getPatientHash().get(hn));
					break;
										
				case 7: // releases a patient out of bed.
					System.out.printf("Enter the bed label of the patient you want to release: ");
					int bedNum = sc.nextInt();
					hs.releasePatient(bedNum);
					break;
										
				case 8: // drop doctor-patient association
					System.out.print("Enter the Doctor's name: "); // Assuming that the user will enter an existing doctor
					String dn = sc.nextLine();
					
					System.out.printf("Enter the patient's health Number: "); // Assuming that the user will enter an existing hNumber
					int hnum = sc.nextInt();				
	
					hs.dropAssociation(hs.getDoctorHash().get(dn), hs.getPatientHash().get(hnum));
					break;
									
				case 9: // display current system state
					hs.displaySystem();
					break;		
				}
			}
			catch (InputMismatchException m) {
				System.out.println("Please enter a valid integer!\n");
			}
			catch (NullPointerException np)
			{
				System.out.println("Health number doesn't exist, or is not in the database!\n");
			}
			catch (ArrayIndexOutOfBoundsException ai) {
				System.out.println("Bed Label out of Range!\n");
			}
		}
	}
}
