package containers;

import java.util.HashMap;

import entities.Patient;

/**
 * A singleton class to access the dictionary containing the patients.
 */
public class PatientSetAccess {
    /**
     * Private constructor to ensure that no instance of this class is created.
     */
    private PatientSetAccess() {}

    /** The dictionary for patients. */
    private static HashMap<Integer, Patient> dictionary;

    /**
     * Return the dictionary that maps health numbers to patients.
     * 
     * @return the dictionary that maps health numbers to patients
     */
    public static HashMap<Integer, Patient> dictionary() {
        if (dictionary == null) {
            /* Create the initial dictionary. */
            dictionary = new HashMap<Integer, Patient>(10);
        }
        return dictionary;
    }
}
