package containers;

import java.util.HashMap;

import entities.Doctor;

/**
 * A singleton class to access the dictionary containing the doctors.
 */
public class DoctorSetAccess {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private DoctorSetAccess() {}

    /** The dictionary for doctors. */
    private static HashMap<String, Doctor> dictionary;

    /**
     * Return the dictionary that maps names to doctors.
     * 
     * @return the dictionary that maps names to doctors
     */
    public static HashMap<String, Doctor> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<String, Doctor>(10);
        }
        return dictionary;
    }
}
