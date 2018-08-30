package entities;

import java.util.LinkedList;

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
	 * or else has a Patient in it.
	 */
	private Patient[] beds;

	/**
	 * Initialize the ward with the name given, and with beds those labels are 
	 * the consecutive integers from minBedLabel to maxBedLabel.
	 * @param wName    the name of the ward
	 * @param wMinBedLabel the label of the first bed in the ward
	 * @param wMaxBedLabel the label of the last bed in the ward
	 * @precond wName != null && !wName.equals("") && wMinBedLabel >= 0 && wMaxBedLabel >= wMinBedLabel
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
		beds = new Patient[wMaxBedLabel - wMinBedLabel + 1];
	}

	/**
	 * Return the name of this ward.
	 * @return  the name of this ward
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Return the smallest label for a bed on the ward.
	 * @return  the smallest Label for a bed on the ward
	 */
	public int getMinBedLabel()
	{
		return minBedLabel;
	}

	/**
	 * Return the largest label for a bed on the ward.
	 * @return  the largest label for a bed on the ward
	 */
	public int getMaxBedLabel()
	{
		return minBedLabel + beds.length - 1;
	}

	/**
	 * Is bedLabel a valid external label for a bed?
	 * @param bedLabel  an int to be tested to determined whether it is a valid label 
	 *             for a bed (from the external/user perspective)
	 * @return  is bedLabel a valid external label for a bed?
	 */
	public boolean isValidLabel(int bedLabel)
	{
		return bedLabel >= minBedLabel && bedLabel <= minBedLabel + beds.length - 1;
	}

	/**
	 * Return the internal/array index of the bed corresponding to the external label.
	 * @param bedLabel the label of a bed from the external/user perspective
	 * @precond isValidLabel(bedLabel)
	 * @return the internal/array index of the bed corresponding to the external label
	 */
	private int externalToInternalIndex(int bedLabel)
	{
		if (! isValidLabel(bedLabel))
			throw new RuntimeException("The value " + bedLabel 
			                           + " is not a valid label for a bed in the ward.");
		
		return bedLabel - minBedLabel;
	}

	/**
	 * Return the external/user label of the bed corresponding to the internal index.
	 * @param arrayIndex the index of a location in the beds array
	 * @precond 0 <= arrayIndex < beds.length
	 * @return the external/user label of the bed corresponding to the internal index
	 */
	private int internalToExternalLabel(int arrayIndex)
	{
		if (arrayIndex < 0 || arrayIndex >= beds.length)
			throw new RuntimeException("The value " + arrayIndex + 
			      " is not a valid index for an array of length " + beds.length + ".");
		
		return arrayIndex + minBedLabel;
	}

	/**
	 * Is the specified bed occupied?
	 * @param bedLabel  the label of the bed to be tested for being occupied
	 * @precond isValidLabel(bedLabel)
	 * @return  is the specified bed occupied?
	 */
	public boolean isOccupied(int bedLabel)
	{
		if (! isValidLabel(bedLabel))
			throw new RuntimeException("The value " + bedLabel 
			                           + " is not a valid label for a bed in the ward.");
		
		return beds[externalToInternalIndex(bedLabel)] != null;
	}

	/**
	 * Assign the specified patient to the specified bed.
	 * @param p  the patient to be assigned a bed
	 * @param bedLabel  the label of the bed that the patient is to be assigned
	 * @precond  isValidLabel(bedLabel) && !isOccupied(bedLabel)
	 */
	public void assignPatientToBed(Patient p, int bedLabel)
	{
		if (! isValidLabel(bedLabel))
			throw new RuntimeException("The value " + bedLabel 
			                           + " is not a valid label for a bed in the ward.");
		
		if (isOccupied(bedLabel))
			throw new RuntimeException("Bed " + bedLabel + " is currently occupied by "
			                           + beds[externalToInternalIndex(bedLabel)]
			                           + " so cannot be assigned to another patient");

		beds[externalToInternalIndex(bedLabel)] = p;
	}

	/**
	 * Return the patient in the specified bed.
	 * @param bedLabel  the label of the bed that has the patient to be retrieved
	 * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
	 * @return  the patient in the specified bed
	 */
	public Patient getPatient(int bedLabel)
	{
		if (! isValidLabel(bedLabel))
			throw new RuntimeException("The value " + bedLabel 
			                           + " is not a valid label for a bed in the ward.");
		
		if (! isOccupied(bedLabel))
			throw new RuntimeException("Bed " + bedLabel + " is not currently occupied"
					                   + " so cannot get its patient");
		return beds[externalToInternalIndex(bedLabel)];
	}

	/**
	 * Return a String representation of the properties of the ward.
	 * @return a String representation of the properties of the ward
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
	 * Return a list of the (bed labels of the) empty beds of the ward.
	 * @return a list of the (bed labels of the) empty beds of the ward
	 */
	public LinkedList<Integer> availableBeds()
	{
		LinkedList<Integer> bedList = new LinkedList<Integer>();
		for (int i = minBedLabel; i <= getMaxBedLabel(); i++)
			if (! isOccupied(i))
				bedList.addLast(i);
		return bedList;
	}

	/**
	 * Remove the patient from a specific bed.
	 * @param bedLabel  the label of the bed from which the patient is to be released
	 * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
	 */
	public void freeBed(int bedLabel)
	{
		if (! isValidLabel(bedLabel))
			throw new RuntimeException("The value " + bedLabel 
			                           + " is not a valid label for a bed in the ward.");
		
		if (! isOccupied(bedLabel))
			throw new RuntimeException("Bed " + bedLabel + " is not currently occupied"
					                   + " so it cannot be freed");
		beds[externalToInternalIndex(bedLabel)] = null;
	}

	/**
	 * A method to test the class.
	 * @param args  not used
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
		Patient p = new Patient("Pete", 123456);
		w.assignPatientToBed(p, 205);
		if (! w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is not occupied.");
		if (w.getPatient(205) != p)
			System.out.println("Person Pete should be in bed 205, but " + w.getPatient(205) + " is.");
		if (! w.getPatient(205).getName().equals("Pete"))
			System.out.println("Pete should be in bed 205, but " + w.getPatient(205) + " is.");
		
		p = new Patient("Sue", 654321);
		w.assignPatientToBed(p, 202);
		if (! w.isOccupied(202))
			System.out.println("Function isOccupied incorrectly returns that bed 202 is not occupied.");
		if (w.getPatient(202) != p)
			System.out.println("Person Sue should be in bed 205, but " + w.getPatient(202) + " is.");
		w.freeBed(205);
		if (w.isOccupied(205))
			System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
		System.out.println("The ward with name 'surgery', beds 200 through 210, " 
                + " and Sue in bed 202 is " + w);
		System.out.println("The empty beds should be 200, 201, 203, 204, 205, 206, 207, "
		                   + "208, 209, and 210, \nand the availableBeds method yields " 
		                   + w.availableBeds());
	}
}
