package containers;

import entities.Ward;

/**
 * A singleton class to access the one ward.
 */
public class WardAccess {
    /**
     * Private constructor to ensure that no instance this class is created.
     */
    private WardAccess() {}

    /** The one instance of Ward. */
    private static Ward ward;

    /**
     * Create and initialize the one instance of a ward.
     * 
     * @param name the name of the ward
     * @param minBedLabel the index of the first bed in the ward
     * @param maxBedLabel the index of the last bed in the ward
     * @precond name != null && !name.equals("") && minBedLabel >= 0 && maxBedLabel >= minBedLabel
     *          && ward == null (must be the first invocation of Initialize)
     */
    public static void initialize(String name, int minBedLabel, int maxBedLabel) {
        if (name == null || name.equals(""))
            throw new RuntimeException("The name of a ward cannot be null or empty.  " + "It is "
                    + name);
        if (minBedLabel < 0 || maxBedLabel < minBedLabel)
            throw new RuntimeException("The bed labels " + minBedLabel + " and " + maxBedLabel
                    + " are invalid as they cannot be negative and at least one bed.");
        if (ward != null)
            throw new RuntimeException("Initialize should only be invoked once.");

        ward = new Ward(name, minBedLabel, maxBedLabel);
    }

    /**
     * Return the ward.
     * 
     * @precond ward != null // i.e., initialize has already been successfully invoked
     * @return the ward
     */
    public static Ward ward() {
        if (ward == null)
            throw new RuntimeException("The ward must be previously initialized"
                    + " before it can be accessed.");

        return ward;
    }
}
