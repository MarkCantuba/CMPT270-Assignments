import java.util.InputMismatchException;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Collection;

/**
 * A simple hospital system with only one ward.  Patients and doctors can be created,
 * and patients assigned to a doctor and/or placed in a bed of the ward.   
 */
public class HospitalSystem 
{
	/**
	 * The scanner used to read input from the user.
	 */
	private Scanner consoleIn;

	/**
	 * The keyed dictionary of all patients.
	 */
	private TreeMap<Integer, Patient> patients;

	/** 
	 * The keyed dictionary of all doctors.
	 */
	private TreeMap<String, Doctor> doctors;

	/**
	 * The ward to be handled.
	 */
	private Ward ward;

	/**
	 * Initialize the system by creating the dictionaries, ward, and input scanner.
	 */
	public void initialize()
	{
		patients = new TreeMap<Integer, Patient>();
		doctors = new TreeMap<String, Doctor>();
		createWard();
	}

	/**
	 * Create the ward after reading the information to initialize it.
	 */
	public void createWard()
	{
		System.out.print("Enter the name of the ward: ");
		String name = consoleIn.nextLine();
		System.out.print("Enter the integer label of the first bed: ");
		int firstBedNum = readInt();
		System.out.print("Enter the integer label of the last bed: ");
		int lastBedNum = readInt();
		if (name != null && !name.equals("") && firstBedNum >= 0 && lastBedNum >= firstBedNum)
			ward = new Ward(name, firstBedNum, lastBedNum);
		else
		{
			System.out.println("The name cannot be null or empty, the label "
			        + "of the first bed must be at least 0, and the label "
			        + "of the last bed must be at least as large as the first label. "
			        + "\nThey are " + name + ", " + firstBedNum + " and " + lastBedNum
			        + "\nTry again.");
			createWard();
		}
	}

	/**
	 * Run the hospital system: initialize, and then accept and carry out operations.
	 * Output the patient and doctor dictionaries, and the ward when finishing.
	 */
	public void run()
	{
		consoleIn = new Scanner(System.in);
		initialize();
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
					addDoctor();
					break;
				case 3:
					assignDoctorToPatient();
					break;
				case 4:
					displayEmptyBeds();
					break;
				case 5:
					assignBed();
					break;
				case 6:
					releasePatient();
					break;
				case 7:
					dropAssociation();
					break;
				case 8:
					System.out.println("The system is as follows: " + toString());
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
		
		System.out.println("The system at shutdown is as follows: " + toString());
		consoleIn.close();
	}

	/**
	 * Output the prompt that lists the possible operations, 
	 * and read the selection chosen by the user.
	 * @return  the int corresponding to the operation selected
	 */
	public int readOpId()
	{
		int id;
		System.out.print("Please select an operation to do"
		                 + "\n0: quit"
		                 + "\n1: add a new patient"
		                 + "\n2: add a new doctor"
		                 + "\n3: assign a doctor to a patient"
		                 + "\n4: display the empty beds of the ward"
		                 + "\n5: assign a patient a bed"
		                 + "\n6: release a patient"
		                 + "\n7: drop doctor-patient association"
		                 + "\n8: display current system state"
		                 + "\nEnter the number of your selection: ");
		id = readInt();
		return id;
	}

	/**
	 * Read in an int value.  If a non-int value is entered, then issue another request.  
	 * @return the int value read in
	 */
	public int readInt()
	{
		int result = 0;    // must be initialized
		boolean successful ;
		do
		{
			successful = true;
			try
			{
				result = consoleIn.nextInt();
			} catch (InputMismatchException e)
			{
				successful = false;
				String faultyInput = consoleIn.nextLine();
				System.out.print("You entered \"" + faultyInput 
				                 + " which is not an int." 
				                 + "\"\nPlease try again: ");
			} 
		}
		while (!successful);
		consoleIn.nextLine();  // discard the remainder of the line
		
		return result;
	}

	/**
	 * Read the information for a new patient and then add the patient
	 * to the dictionary of all patients.
	 */
	public void addPatient()
	{
		System.out.print("Enter the name of the patient: ");
		String name = consoleIn.nextLine();
		System.out.print("Enter the health number of the patient: ");
		int healthNum = readInt();
		if (patients.containsKey(healthNum))
		{
			throw new RuntimeException("Patient not added as there already "
			                    + "is a patient with the health number " + healthNum);
		}
		else
		{
			Patient p = new Patient(name, healthNum);
			Patient sameNumberPatient = patients.put(healthNum, p);
			if (sameNumberPatient != null)
			{
				patients.put(healthNum, sameNumberPatient);  // put the original patient back
				throw new RuntimeException("Health number in the patient dictionary even "
				       + "though containsKey failed.  Number " + healthNum + " not entered.");
			}
		}
	}

	/**
	 * Read the information for a new doctor and then add the doctor 
	 * to the dictionary of all doctors.
	 */
	public void addDoctor()
	{
		System.out.print("Enter the name of the doctor: ");
		String name = consoleIn.nextLine();
		if (doctors.containsKey(name))
			throw new RuntimeException("Doctor not added as there already "
			                           + "is a doctor with the name " + name);
		System.out.print("Is the doctor a surgeon? (yes or no)");
		String response = consoleIn.nextLine();
		Doctor d;
		if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
			d = new Surgeon(name);
		else
			d = new Doctor(name);
		Doctor sameNumberDoctor = doctors.put(name, d);
		if (sameNumberDoctor != null)
		{
			doctors.put(name, sameNumberDoctor); // put the original doctor back
			throw new RuntimeException("Name in the doctor dictionary even though "
			               + "containsKey failed.  Name "  + name + " not entered.");
		}
	}

	/**
	 * Assign a doctor to take care of a patient.
	 */
	public void assignDoctorToPatient()
	{
		Patient p = obtainPatient();
		System.out.print("Enter the name of the doctor: ");
		String name = consoleIn.nextLine();
		Doctor d = doctors.get(name);
		if (d == null)
			throw new RuntimeException("There is no doctor with name " + name);
		else
		{
			p.addDoctor(d);
			d.addPatient(p);
		}
	}

	/**
	 * Read a health number and return the patient with this number.
	 * @return the patient with the health number read in
	 */
	public Patient obtainPatient()
	{
		System.out.print("Enter the health number of the patient: ");
		int healthNumber = readInt();
		Patient p = patients.get(healthNumber);
		if (p == null)
			throw new RuntimeException("There is no patient with health number "
			                           + healthNumber);
		return p;
	}

	/**
	 * Display the empty beds for the ward.
	 */
	public void displayEmptyBeds()
	{
		System.out.println("The empty beds of the ward are " + ward.availableBeds());
	}

	/**
	 * Assign a patient to a specific bed.
	 */
	public void assignBed()
	{
		Patient p = obtainPatient();
		if (p.getBedLabel() != -1)
			throw new RuntimeException(" Patient " + p
					+ " is already in a bed so cannot be assigned a new bed");
		System.out.print("Enter the bed number for the patient: ");
		int bedNum = readInt();
		if (bedNum < ward.getMinBedLabel() || bedNum > ward.getMaxBedLabel())
			throw new RuntimeException("Bed label " + bedNum + " is not valid, as "
					+ "the value must be between " + ward.getMinBedLabel() 
					+ " and " + ward.getMaxBedLabel());
		p.setBedLabel(bedNum);
		ward.assignPatientToBed(p, bedNum);
	}

	/** 
	 * Release a patient from the ward.
	 */
	public void releasePatient()
	{
		Patient p = obtainPatient();
		if (p.getBedLabel() == -1)
			throw new RuntimeException("The patient must already have a bed.");
		int bedNum = p.getBedLabel();
		if (ward.getPatient(bedNum) != p)
			throw new RuntimeException("The patient is not in the bed stored"
					+ " with the patient.  Bed " + bedNum
					+ " has patient " + ward.getPatient(bedNum));
		ward.freeBed(p.getBedLabel());
		p.release();
	}

	/**
	 * Drop the association between a doctor and a patient.
	 */
	public void dropAssociation()
	{
		Patient p = obtainPatient();
		System.out.print("Enter the name of the doctor: ");
		String name = consoleIn.nextLine();
		Doctor d = doctors.get(name);
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
	 * Return a String that contains all the patients and doctors in the system.
	 * @return a String that contains all the patients and doctors in the system.
	 */
	public String toString()
	{
		String result = "\nThe patients in the system are \n";
		Collection<Patient> patientsColl = patients.values();
		for (Patient p: patientsColl)
			result = result + p;
		result = result + "\nThe doctors in the system are \n";
		Collection<Doctor> doctorsColl = doctors.values();
		for (Doctor d: doctorsColl)
			result = result + d;
		result = result + "\nThe ward is " + ward;
		return result;
	}

	/**
	 * Run the hospital system.
	 * @param args not used
	 */
	public static void main(String[] args) 
	{
		HospitalSystem sys = new HospitalSystem();
		sys.run();
	}
}
