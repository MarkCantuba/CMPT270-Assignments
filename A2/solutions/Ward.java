/**
 * A ward of a hospital with a specified number of beds with consecutive labels.
 */
public class Ward 
{
	/** 
	 * The name of this ward.
	 */
	private String name;

	/**
	 * The (external) label of the first bed of the ward. 
	 */
	private int minBedLabel;

	/**
	 * An array to represent the beds of the ward.  Each bed is empty (null) 
	 * or else has a Person in it.
	 */
	private Person[] beds;

	/**
	 * Initialize the ward with the name given, and with beds those labels are 
	 * the consecutive integers from minBedLabel to maxBedLabel.
	 * 
	 * @param wName the name of the ward
	 * @param wMinBedLabel the label of the ward's first bed
	 * @param wMaxBedLabel the label of the ward's last bed
	 */
	public Ward(String wName, int wMinBedLabel, int wMaxBedLabel)
	{
		if (wName == null || wName.equals(""))
			throw new RuntimeException("The name of a ward cannot be null or empty.  " 
			                           + "It is " + wName);
		if (wMinBedLabel < 0 || wMaxBedLabel < wMinBedLabel)
			throw new RuntimeException("The bed labels " + wMinBedLabel + " and " + wMaxBedLabel 
			                  + " are invalid as they cannot be negative and must have at least one bed.");
		
		name = wName;
		minBedLabel = wMinBedLabel;
		beds = new Person[wMaxBedLabel - wMinBedLabel + 1];
	}

	/**
	 * Return the name of this ward.
	 *
	 * @return the name of this ward
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Return the smallest label for a bed on the ward.
	 * 
	 * @return the label for the first bed in the ward
	 */
	public int getMinBedLabel()
	{
		return minBedLabel;
	}

	/**
	 * Return the largest label for a bed on the ward.
	 * 
	 * @return the label for the last bed in the ward
	 */
	public int getMaxBedLabel()
	{
		return minBedLabel + beds.length - 1;
	}

	/**
	 * Is bedLabel a valid external label for a bed?
	 * 
	 * @param bedLabel a candidate bed label
	 * @return whether or not the provided label is within the range of possible labels
	 */
	public boolean isValidLabel(int bedLabel)
	{
		return bedLabel >= minBedLabel && bedLabel <= minBedLabel + beds.length - 1;
	}

	/**
	 * Return the internal/array index of the bed corresponding to the external label.
	 *  
	 * @param bedLabel a bed label
	 * @return the index in the bed array corresponding to the provided label
	 */
	private int externalToInternalIndex(int bedLabel)
	{
		return bedLabel - minBedLabel;
	}

	/**
	 * Return the external/user label of the bed corresponding to the internal index.
	 * 
	 * @param arrayIndex an index for the array of beds
	 * @return the bed label corresponding to the provided index
	 */
	private int internalToExternalLabel(int arrayIndex)
	{
		return arrayIndex + minBedLabel;
	}

	/**
	 * Is the specified bed occupied?
	 * 
	 * @param bedLabel a bed label
     * @return whether or not the bed corresponding to the provided label is occupied by a patient
	 */
	public boolean isOccupied(int bedLabel)
	{
		return beds[externalToInternalIndex(bedLabel)] != null;
	}

	/**
	 * Assign the specified person to the specified bed.
	 * 
	 * @param p the person to assign to a bed
	 * @param bedLabel a bed label
	 */
	public void assignPatientToBed(Person p, int bedLabel)
	{
		beds[externalToInternalIndex(bedLabel)] = p;
	}

	/**
	 * Return the person in the specified bed.
	 * 
	 * @param bedLabel a bed label
	 * @return the person that is assigned to the bed cooresponding to the provided bed label
	 */
	public Person getPatient(int bedLabel)
	{
		return beds[externalToInternalIndex(bedLabel)];
	}

	/**
	 * Return a String representation of the properties of the ward.
	 * 
	 * @return a String representation of the ward
	 */
	public String toString()
	{
		String result = "\nWard " + name + " with capacity " + beds.length
		                + " has the following patients: ";
		for (int i = 0; i < beds.length; i++)
		{
			result = result + "\nbed " + internalToExternalLabel(i) + ": ";
			if (beds[i] != null)
				result = result + beds[i].getName();
		}
		return result + "\n";
	}

	/**
	 * A method to test the class.
	 */
	public static void main(String[] args)
	{
		Ward w = new Ward("surgery", 200, 210);
		if (!w.getName().equals("surgery"))
			System.out.println("The ward does not have name surgery.");
		if (w.getMinBedLabel() != 200)
			System.out.println("The ward does not have the correct minimum bed label.");
		if (w.getMaxBedLabel() != 210)
			System.out.println("The ward does not have the correct maximum bed label.");
		
		/* Test some int values to determine whether they are valid external label values. */
		if (!w.isValidLabel(200) || !w.isValidLabel(201)
		        || !w.isValidLabel(210) || !w.isValidLabel(209)
		        ||  w.isValidLabel(199) 
		        ||  w.isValidLabel(211))
			System.out.println("Error in the test for a valid external label.");
		/* Test the conversion methods from external label to internal index and back. */
		if (w.internalToExternalLabel(w.externalToInternalIndex(200)) != 200
		    || w.internalToExternalLabel(w.externalToInternalIndex(210)) != 210
		    || w.internalToExternalLabel(w.externalToInternalIndex(205)) != 205)
			System.out.println("Error in converting external label to internal and back.");
		/* Test the conversion methods from internal index to external label and back. */
		if (w.externalToInternalIndex(w.internalToExternalLabel(0)) != 0
		    || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length-1)) != w.beds.length-1
		    || w.externalToInternalIndex(w.internalToExternalLabel(w.beds.length/2)) != w.beds.length/2)
				System.out.println("Error in converting internal index to external and back.");
		/* Check the mapping of internal and external indices. */
		if (w.externalToInternalIndex(200) != 0)
			System.out.println("Minimum external label was not converted to 0.");
		if (w.externalToInternalIndex(210) != w.beds.length-1)
			System.out.println("Maximum external label was not converted "
			                   + "to last location of the array.");
		if (w.internalToExternalLabel(0) != 200)
			System.out.println("Minimum internal index was not converted to first bed label.");
		if (w.internalToExternalLabel(w.beds.length-1) != 210)
			System.out.println("Maximum internal index was not converted "
			                   + "to last bed label.");
		
		if (w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
		Person p = new Person("Pete", 123456);
		w.assignPatientToBed(p, 205);
		if (! w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is not occupied.");
		if (w.getPatient(205) != p)
			System.out.println("Person Pete should be in bed 205, but " + w.getPatient(205) + " is.");
		if (! w.getPatient(205).getName().equals("Pete"))
			System.out.println("Pete should be in bed 205, but " + w.getPatient(205) + " is.");
		System.out.println("The ward with name 'surgery', beds 200 through 210, " 
				+ " and Pete in bed 205 is " + w);

		p = new Person("Sue", 654321);
		w.assignPatientToBed(p, 202);
		if (! w.isOccupied(202))
			System.out.println("Function isOccupied incorrectly returns that bed 202 is not occupied.");
		if (w.getPatient(202) != p)
			System.out.println("Person Sue should be in bed 205, but " + w.getPatient(202) + " is.");
		if (! w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
	}
}
