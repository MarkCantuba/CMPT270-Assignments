package commands;

import entities.Patient;
import entities.Doctor;
import containers.PatientSetAccess;
import containers.DoctorSetAccess;

/**
 * Command to assign a doctor to a patient.
 */
public class AssignDoctorCommand extends CommandStatus {
    /**
     * Assign the doctor with the specified name to handle the patient with the specified health
     * number.
     * 
     * @param name the name of the doctor
     * @param patientNum the health number of the patient
     */
    public void assignDoctor(String name, int patientNum) {
        successful = true;
        Patient p = PatientSetAccess.dictionary().get(patientNum);
        if (p == null) {
            successful = false;
            errorMessage = "There is no patient with health number " + patientNum;
        }

        Doctor d = DoctorSetAccess.dictionary().get(name);
        if (d == null) {
            if (successful) {
                successful = false;
                errorMessage = "There is no doctor with name " + name;
            } else
                errorMessage = errorMessage + " and there is no doctor with name " + name;
        }

        if (successful) {
            try {
                p.addDoctor(d);
                d.addPatient(p);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
            }
        }
    }
}
