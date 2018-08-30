package commands;

import containers.WardAccess;

/**
 * The command to create and initialize the one instance of Ward.
 */
public class CreateWardCommand extends CommandStatus {
    /**
     * Create and initialize the one instance of a ward.
     * 
     * @param name the name of the ward
     * @param minBedLabel the index of the first bed in the ward
     * @param maxBedLabel the index of the last bed in the ward
     */
    public void createWard(String name, int minBedLabel, int maxBedLabel) {
        if (name == null || name.equals("")) {
            successful = false;
            errorMessage = "The name of a ward cannot be null or empty.  " + "It is " + name;
            return;
        }
        if (minBedLabel < 0 || maxBedLabel < minBedLabel) {
            successful = false;
            errorMessage =
                    "The bed indices " + minBedLabel + " and " + maxBedLabel
                            + " are invalid as they cannot be negative "
                            + "and include at least one bed.";
            return;
        }

        try {
            WardAccess.initialize(name, minBedLabel, maxBedLabel);
            successful = true;
        } catch (RuntimeException e) {
            successful = false;
            errorMessage = e.getMessage();
        }
    }
}
