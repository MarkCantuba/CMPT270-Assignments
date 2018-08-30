package commands;

import entities.Patient;
import entities.Ward;
import containers.PatientSetAccess;
import containers.WardAccess;

/**
 * Command to assign a patient to a specific bed in the ward.
 */
public class AssignBedCommand extends CommandStatus {
    /**
     * Assign a patient to a specific bed.
     * 
     * @param patientNum the health number of the patient to be assigned a bed
     * @param bedLabel the integer label of the bed to be assigned to the patient
     */
    public void assignBed(int patientNum, int bedLabel) {
        Patient p = null;
        if (!PatientSetAccess.dictionary().containsKey(patientNum)) {
            successful = false;
            errorMessage =
                    "The patient must be already in the system.  There is "
                            + "no patient with the health number " + patientNum;
            return;
        }
        p = PatientSetAccess.dictionary().get(patientNum);
        successful = true;

        Ward ward = WardAccess.ward();
        if (bedLabel < ward.getMinBedLabel() || bedLabel > ward.getMaxBedLabel()) {
            successful = false;
            errorMessage =
                    "Bed label " + bedLabel + " is not valid, as the value " + "must be between "
                            + ward.getMinBedLabel() + " and " + ward.getMaxBedLabel();
            return;
        }

        if (p.getBedLabel() != -1) {
            successful = false;
            errorMessage = "The patient is already in the bed with label " + p.getBedLabel();
            return;
        }

        if (ward.isOccupied(bedLabel)) {
            successful = false;
            errorMessage = "Bed " + bedLabel + " is already occupied.";
            return;
        }

        try {
            p.setBedLabel(bedLabel);
            ward.assignPatientToBed(p, bedLabel);
        } catch (RuntimeException e) {
            successful = false;
            errorMessage = e.getMessage();
        }
    }
}
