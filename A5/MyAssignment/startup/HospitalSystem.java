package startup;

import userInterfaces.InputOutputInterface;
import userInterfaces.ConsoleIO;
import userInterfaces.DialogIO;

import commands.AddPatientCommand;
import commands.EmptyBedsCommand;
import commands.CurrentStateCommand;
import commands.AddDoctorCommand;
import commands.CreateWardCommand;
import commands.AssignDoctorCommand;
import commands.AssignBedCommand;
import commands.ReleasePatientCommand;
import commands.DropAssocCommand;

/**
 * A simple hospital system with only one ward. Patients and doctors can be created, and patients
 * assigned to a doctor and/or placed in a bed of the ward.
 */
public class HospitalSystem {
    /**
     * The interface to be used to read input from the user and output results to the user.
     */
    private InputOutputInterface ioInterface;

    /**
     * Initialize the system by creating the dictionaries, ward, and interface for I/O.
     */
    public void initialize() {
        ioInterface = new DialogIO();
        String option = ioInterface.readString("Should dialog boxes be used for I/O? (Y/N) ");
        if (option != null)
            if (option.charAt(0) == 'N' || option.charAt(0) == 'n')
                ioInterface = new ConsoleIO();
        createWard();
    }

    /**
     * Create the ward after reading the information to initialize it.
     */
    public void createWard() {
        String name = ioInterface.readString("Enter the name of the ward: ");
        int firstBedLabel = ioInterface.readInt("Enter the integer label of the first bed: ");
        int lastBedLabel = ioInterface.readInt("Enter the integer label of the last bed: ");
        CreateWardCommand createWard = new CreateWardCommand();
        createWard.createWard(name, firstBedLabel, lastBedLabel);
        if (!createWard.wasSuccessful()) {
            ioInterface.outputString(createWard.getErrorMessage() + "\n");
            createWard();
        }
    }

    /**
     * Run the hospital system: initialize, and then accept and carry out operations. Output the
     * patient and doctor dictionaries, and the ward when finishing.
     */
    public void run() {
        initialize();
        int opId = readOpId();
        while (opId != 0) {
            switch (opId) {
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
                    displaySystemState();
                    break;
                default:
                    ioInterface.outputString("Invalid int value; try again\n");
            }

            opId = readOpId();
        }

        displaySystemState();
        System.exit(0);
    }

    /**
     * Output the prompt that lists the possible tasks, and read the selection chosen by the user.
     * 
     * @return the int corresponding to the task selected
     */
    public int readOpId() {
        String[] taskChoices =
                new String[] {"quit", "add a new patient", "add a new doctor",
                        "assign a doctor to a patient", "display the empty beds of the ward",
                        "assign a patient a bed", "release a patient",
                        "drop doctor-patient association", "display current system state"};

        return ioInterface.readChoice(taskChoices);
    }

    /**
     * Read the information for a new patient and then add the patient to the dictionary of all
     * patients.
     */
    public void addPatient() {
        String name = ioInterface.readString("Enter the name of the patient: ");
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");
        AddPatientCommand addPat = new AddPatientCommand();
        addPat.addPatient(name, healthNum);
        if (!addPat.wasSuccessful())
            ioInterface.outputString(addPat.getErrorMessage() + "\n");
    }

    /**
     * Read the information for a new doctor and then add the doctor to the dictionary of all
     * doctors.
     */
    public void addDoctor() {
        String name = ioInterface.readString("Enter the name of the doctor: ");
        String response = ioInterface.readString("Is the doctor a surgeon? (yes or no)");
        AddDoctorCommand addDoc = new AddDoctorCommand();
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            addDoc.addDoctor(name, true);
        else
            addDoc.addDoctor(name, false);
        if (!addDoc.wasSuccessful())
            ioInterface.outputString(addDoc.getErrorMessage() + "\n");
    }

    /**
     * Assign a doctor to take care of a patient.
     */
    public void assignDoctorToPatient() {
        int healthNumber = ioInterface.readInt("Enter the health number of the patient: ");
        String name = ioInterface.readString("Enter the name of the doctor: ");
        AssignDoctorCommand assignDoc = new AssignDoctorCommand();
        assignDoc.assignDoctor(name, healthNumber);
        if (!assignDoc.wasSuccessful())
            ioInterface.outputString(assignDoc.getErrorMessage() + "\n");
    }

    /**
     * Display the empty beds for the ward.
     */
    public void displayEmptyBeds() {
        EmptyBedsCommand emptyBeds = new EmptyBedsCommand();
        emptyBeds.findEmptyBedsList();
        ioInterface.outputString("The empty beds of the hospital are "
                + emptyBeds.getEmptyBedsList() + "\n");
    }

    /**
     * Assign a patient to a specific bed.
     */
    public void assignBed() {
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");
        int bedNum = ioInterface.readInt("Enter the bed number for the patient: ");
        AssignBedCommand assignBed = new AssignBedCommand();
        assignBed.assignBed(healthNum, bedNum);
        if (!assignBed.wasSuccessful())
            ioInterface.outputString(assignBed.getErrorMessage() + "\n");
    }

    /**
     * Release a patient from the ward.
     */
    public void releasePatient() {
        int healthNum = ioInterface.readInt("Enter the health number of the patient: ");
        ReleasePatientCommand release = new ReleasePatientCommand();
        release.releasePatient(healthNum);
        if (!release.wasSuccessful())
            ioInterface.outputString(release.getErrorMessage() + "\n");
    }

    /**
     * Drop the association between a doctor and a patient.
     */
    public void dropAssociation() {
        int healthNumber = ioInterface.readInt("Enter the health number of the patient: ");
        String name = ioInterface.readString("Enter the name of the doctor: ");
        DropAssocCommand dropAssoc = new DropAssocCommand();
        dropAssoc.dropAssociation(name, healthNumber);
        if (!dropAssoc.wasSuccessful())
            ioInterface.outputString(dropAssoc.getErrorMessage() + "\n");
    }

    public void displaySystemState() {
        CurrentStateCommand state = new CurrentStateCommand();
        state.findCurState();
        ioInterface.outputString("The system is as follows: " + state.getCurState() + "\n");
    }

    /**
     * Return a String that contains all the patients and doctors in the system.
     * 
     * @return a String that contains all the patients and doctors in the system.
     */
    public String toString() {
        CurrentStateCommand state = new CurrentStateCommand();
        state.findCurState();
        return "The system is as follows: " + state.getCurState() + "\n";
    }

    /**
     * Run the hospital system.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        HospitalSystem sys = new HospitalSystem();
        sys.run();
    }
}
