package commands;

import entities.Patient;
import containers.PatientSetAccess;

/**
 * Command to add a patient into the system.
 */
public class AddPatientCommand extends CommandStatus {
    /**
     * Create a patient with the specified name and health number, and add the patient into the
     * system.
     * 
     * @param name the name of the patient to be added
     * @param healthNum the health number of the patient to be added
     */
    public void addPatient(String name, int healthNum) {
        if (PatientSetAccess.dictionary().containsKey(healthNum)) {
            successful = false;
            errorMessage =
                    "Patient not added as there already " + "is a patient with the health number "
                            + healthNum;
        } else {
            Patient p = null;
            try {
                p = new Patient(name, healthNum);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
                return;
            }
            Patient sameNumberPatient = PatientSetAccess.dictionary().put(healthNum, p);
            if (sameNumberPatient != null) {
                // put the original patient back
                PatientSetAccess.dictionary().put(healthNum, sameNumberPatient);
                successful = false;
                errorMessage =
                        "The health number is in the patient dictionary even "
                                + "though containsKey failed.  Number " + healthNum
                                + " not entered.";
            } else
                successful = true;
        }
    }
}
