package commands;

import entities.Surgeon;
import entities.Doctor;
import containers.DoctorSetAccess;

/**
 * Command to add a doctor into the system.
 */
public class AddDoctorCommand extends CommandStatus {
    /**
     * Create a doctor with the specified name, and add the doctor into the system. If specified,
     * make the doctor a surgeon.
     * 
     * @param name the name of the doctor to be added
     * @param isSurgeon is the doctor a surgeon?
     */
    public void addDoctor(String name, boolean isSurgeon) {
        if (DoctorSetAccess.dictionary().containsKey(name)) {
            successful = false;
            errorMessage =
                    "Doctor not added as there already " + "is a doctor with the name " + name;
        } else {
            Doctor d = null;
            try {
                if (isSurgeon)
                    d = new Surgeon(name);
                else
                    d = new Doctor(name);
            } catch (RuntimeException e) {
                successful = false;
                errorMessage = e.getMessage();
                return;
            }
            Doctor sameNameDoctor = DoctorSetAccess.dictionary().put(name, d);
            if (sameNameDoctor != null) {
                // put the original doctor back
                DoctorSetAccess.dictionary().put(name, sameNameDoctor);
                successful = false;
                errorMessage =
                        "The name is in the doctor dictionary even though "
                                + "containsKey failed.  Name " + name + " not entered.";
            } else
                successful = true;
        }
    }
}
