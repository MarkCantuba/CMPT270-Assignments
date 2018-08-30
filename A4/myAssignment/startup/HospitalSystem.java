package startup;

import java.util.Collection;

import commands.*;
import containers.WardAccess;
import containers.doctorAccess;
import containers.patientAccess;
import entities.Doctor;
import entities.Patient;
import entities.Surgeon;
import userInterfaces.*;

/**
 * Hospital System has been modified to act as a controller, but has the same functionalities.
 */
public class HospitalSystem {
	private CommandStatus cs;
	private ConsoleIO cio;
	private DialogIO dio;
	private int ioStyle;
	
	/**
	 * A Constructor class to initialize instance variables!
	 */
	public HospitalSystem() {
		cs = new CommandStatus();
		cio = new ConsoleIO();
		dio = new DialogIO();
		ioStyle = 0;
	}
	
	/**
	 * Method that allows a user to pick which style dialog box they would like
	 * @return
	 */
	public int chooseIOstyle() {
		int userChoice = dio.readInt("Please Select Desired Interface IO\n"
				+ "1 - Console Style\n"
				+ "2 - Dialog Box Style");
		while(userChoice < 1 || userChoice > 2) {
			return chooseIOstyle();
		}
		ioStyle = userChoice;
		return userChoice;	
	}
	
	/**
	 * Creates a ward. The style of IO will be reliant on chooseIOstyle(). If
	 * User picks 1, then the IO Style for create Ward will be Console based, otherwise
	 * will be Dialog. After all required parameters are obtained, WardAccess will
	 * be initialized along with the patient and doctor containers.
	 */
	public void createWard() {
		String wardName;
		int minBed;
		int maxBed;
		if (ioStyle == 1) {
			wardName = cio.readString("Please enter Desired Ward Name: ");
			minBed = cio.readInt("Enter Minimum Bed Label: ");
			maxBed = cio.readInt("Enter Maximum Bed Label: ");			
		}
		else {
			wardName = dio.readString("Please enter Desired Ward Name: ");
			minBed = dio.readInt("Enter Minimum Bed Label: ");
			maxBed = dio.readInt("Enter Maximum Bed Label: ");	
		}
		
		try {
			WardAccess.initialize(wardName, minBed, maxBed);
		}
		
		catch (RuntimeException re) {
			System.out.println("Invalid Input! Please re enter required credentials!");
		}	
	}
	
	/**
	 * Output the prompt that lists the possible operations, 
	 * and read the selection chosen by the user. The interface
	 * will depend on interface choice! Dialog Box option
	 * only supports add new patient, display empty beds,
	 * assign patient to bed and release patient
	 * @return  the int corresponding to the operation selected
	 */
	public int readOpId() {
		String[] array = {    "0: quit"
				, "1: add a new patient"
				, "2: display the empty beds of the ward"
				, "3: assign a patient a bed"
				, "4: release a patient"};
		int choice;
		if (ioStyle == 1) {
			choice = cio.readChoice(array);
		}
		else {
			choice = dio.readChoice(array);
		}
		return choice;
	}
	
	/**
	 * Read the information for a new patient and then add the patient
	 * to the dictionary of all patients. As usual, interface will
	 * be dependent on the user.
	 */
	public void addPatient() {
		newPatientCmd p = new newPatientCmd();
		String wname;
		int hNum;
		
		if (ioStyle == 1) {
			wname = cio.readString("Please enter the Patient's name: ");
			hNum = cio.readInt("Please enter the Patient's health number: ");
		}
		else {
			wname = dio.readString("Please enter the Patient's name: ");
			hNum = dio.readInt("Please enter the Patient's health number: ");
		}
		
		try {
			p.addPatient(wname, hNum);
		}
		catch (RuntimeException re) {
			System.out.println("Invalid Name or health Number! Please re enter required credentials!");
		}

	}
			
	/**
	 * Display the empty beds for the ward. Interface will depend on what the user want
	 * if 1, empty beds will be displayed in console, otherwise will be displayed on a dialog box.
	 */
	public void displayEmptyBeds() {
		getEmptyBedsCmd empty = new getEmptyBedsCmd();
		if (ioStyle == 1) {
			cio.outputString(empty.getEmptyBeds());
		}
		else {
			dio.outputString(empty.getEmptyBeds());
		}
	}
	
	/**
	 * Assign a patient to a specific bed.
	 */
	public void assignBed() {
		assignPatientToBedCmd apc = new assignPatientToBedCmd(); 
		int bNumber;
		int hNumber;
		if (ioStyle == 1) {
			hNumber = cio.readInt("Enter the patient's health number: ");
			bNumber = cio.readInt("Enter Bed number you wish to assign patient to: ");
		}
		else {
			hNumber = dio.readInt("Enter the patient's health number: ");
			bNumber = dio.readInt("Enter Bed number you wish to assign patient to: ");
		}
		try {
			apc.assignToBed(hNumber, bNumber);
		}
		catch (RuntimeException re) {
			System.out.println("Invalid Input! Please enter valid credentials!");
		}
	}
	
	/** 
	 * Release a patient from the ward.
	 */
	public void releasePatient() {
		releasePatientCmd rp = new releasePatientCmd();
		int bedNum;
		if (ioStyle == 1) {
			bedNum = cio.readInt("Enter the bed Number of the patient you want to release: ");
		}
		else {
			bedNum = dio.readInt("Enter the bed Number of the patient you want to release: ");
		}
		try {
			rp.releasePatient(bedNum);	
		}
		catch (RuntimeException re) {
			System.out.println("Invalid Input! Please enter valid bed number!");
		}		
	}
	
	
	/**
	 * Run the hospital system: initialize, and then accept and carry out operations.
	 * Output the patient and doctor dictionaries, and the ward when finishing.
	 */
	public void run()
	{
		int opId = readOpId();
		while (opId != 0)
		{
			try
			{
				switch (opId)
				{
				case 1:
					addPatient();
					break;
				case 2:
					displayEmptyBeds();
					break;
				case 3:
					assignBed();
					break;
				case 4:
					releasePatient();
					break;
				default:
					System.out.println("Invalid task specification; try again\n");
				}
			} catch (RuntimeException e)
			{
				System.out.println(e.getMessage());
			}

			opId = readOpId();
		}

	}
	
	/**
	 * Read the information for a new doctor and then add the doctor 
	 * to the dictionary of all doctors.
	 */
	public void addDoctor() {
		String name = cio.readString("Enter the name of the doctor: ");
		if (doctorAccess.dict().containsKey(name))
			throw new RuntimeException("Doctor not added as there already "
			                           + "is a doctor with the name " + name);
		
		String response = cio.readString("Is the doctor a surgeon? (yes or no): ");
		Doctor d;
		if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
			d = new Surgeon(name);
		else
			d = new Doctor(name);
		Doctor sameNumberDoctor = doctorAccess.dict().put(name, d);
		if (sameNumberDoctor != null)
		{
			doctorAccess.dict().put(name, sameNumberDoctor); // put the original doctor back
			throw new RuntimeException("Name in the doctor dictionary even though "
			               + "containsKey failed.  Name "  + name + " not entered.");
		}
	}
	
	/**
	 * Read a health number and return the patient with this number.
	 * @return the patient with the health number read in
	 */
	public Patient obtainPatient() {
		int healthNumber = cio.readInt("Enter the health number of the patient: ");
		Patient p = patientAccess.dict().get(healthNumber);
		if (p == null)
			throw new RuntimeException("There is no patient with health number "
			                           + healthNumber);
		return p;
	}
	
	/**
	 * Assign a doctor to take care of a patient.
	 */
	public void assignDoctorToPatient() {
		Patient p = obtainPatient();
		String name = cio.readString("Enter the name of the doctor: ");
		Doctor d = doctorAccess.dict().get(name);
		if (d == null)
			throw new RuntimeException("There is no doctor with name " + name);
		else
		{
			p.addDoctor(d);
			d.addPatient(p);
		}
	}
	
	/**
	 * Drop the association between a doctor and a patient.
	 */
	public void dropAssociation()
	{
		Patient p = obtainPatient();

		String name = cio.readString("Enter the name of the doctor: ");
		Doctor d = doctorAccess.dict().get(name);
		if (d == null)
			throw new RuntimeException("There is no doctor with name " + name);
		
		int healthNumber = p.getHealthNumber();
		if (!d.hasPatient(healthNumber))
			throw new RuntimeException("This doctor is not associated with this patient.");
		if (!p.hasDoctor(name))
			throw new RuntimeException("This doctor and this patient are incorrectly "
			               + "associated.  The doctor has the patient, " 
			               + "but the patient does not have the doctor");
		
		p.removeDoctor(name);
		d.removePatient(healthNumber);
	}
	
	/**
	 * toString method that allows the class to be printable. Displays
	 * current system state
	 */
	public String toString()
	{
		String result = "\nThe patients in the system are \n";
		Collection<Patient> patientsColl = patientAccess.dict().values();
		for (Patient p: patientsColl)
			result = result + p;
		result = result + "\nThe doctors in the system are \n";
		Collection<Doctor> doctorsColl = doctorAccess.dict().values();
		for (Doctor d: doctorsColl)
			result = result + d;
		result = result + "\nThe ward is " + WardAccess.ward().getName();
		return result;
	}
	
	/**
	 * Get's the last method's exit status
	 * @return: true if output is successful, otherwise false!
	 */
	public boolean getLastExecutionStatus() {
		return cs.wasSuccessful();
	}
	
}
