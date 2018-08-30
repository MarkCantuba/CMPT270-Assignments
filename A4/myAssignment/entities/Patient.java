package entities;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * This class is to model a patient in a hospital.  The class extends class Person
 * and has a bed in the ward (with -1 value for no bed), and a list of doctors.
 */
public class Patient extends Person 
{
	/**
	 * The integer label of the bed occupied by the patient.
	 * A value of -1 indicates no bed at this time.
	 */
	private int bedLabel;

	/**
	 * The list of the doctors of the patient.
	 */
	private LinkedList<Doctor> doctors;

	/**
	 * Initialize an instance with the given name and health number.
	 * @param name		the name for the person
	 * @param number	the health number for the person
	 * @precond name != null && !name.equals("") && number > 0
	 */
	public Patient(String name, int number)
	{
		super(name, number);
		bedLabel = -1;
		doctors = new LinkedList<Doctor>();
	}

	/**
	 * Assign the patient to the bed whose label is passed in as a parameter.
	 * @param bedLabel	the integer label of the bed for the patient
	 */
	public void setBedLabel(int bedLabel)
	{
		this.bedLabel = bedLabel;
	}

	/**
	 * Return the integer label of the bed occupied by the patient.
	 * @return  the integer label of the bed occupied by the patient
	 */
	public int getBedLabel()
	{
		return bedLabel;
	}

	/**
	 * Release the patient from the ward by removing the bed associated 
	 * with the patient.
	 */
	public void release()
	{
		bedLabel = -1;
	}

	/**
	 * Add another doctor to the list of doctors of this patient.
	 * @param d	the new doctor to be added for this patient
	 * @precond !hasDoctor(d.getName())
	 */
	public void addDoctor(Doctor d)
	{
		if (hasDoctor(d.getName()))
				throw new RuntimeException(d.getName() 
				                           + " is already a doctor for this patient");
		doctors.add(d);
	}

	/**
	 * Does this patient have a doctor with the name specified by the parameter?
	 * @param name	the name of the doctor to be tested for handling this patient
	 * @return does this patient have a doctor with the name specified by the parameter?
	 */
	public boolean hasDoctor(String name)
	{
		Iterator<Doctor> iter = doctors.iterator();
		while (iter.hasNext())
		{
			Doctor d = iter.next();
			if (d.getName().equals(name))
				return true;
		}
		return false;
	}

	/** Remove the doctor specified by the name parameter 
	 * from the doctors for this patient.
	 * @param name	the name of the doctor to be removed from the doctors list
	 * @precond hasDoctor(name)
	 */
	public void removeDoctor(String name)
	{
		Iterator<Doctor> iter = doctors.iterator();
		while (iter.hasNext())
		{
			Doctor d = iter.next();
			if (d.getName().equals(name))
			{
				iter.remove();
				return;
			}
		}
		throw new RuntimeException("Patient " + getHealthNumber() 
		            + " does not have a doctor with name " + name);
	}

	/**
	 * Return a string representation of the patient
	 * @return a string representation of the patient
	 */
	public String toString()
	{
		String result = super.toString();
		if (bedLabel != -1)
			result = result + "in bed " + bedLabel + " with doctors ";
		else
			result = result + "with doctors ";
		for (Doctor d: doctors)
			result = result + " " + d.getName();
		return result + "\n";
	}

	/**
	 * Carry out basic tests of this class.
	 */
	public static void main(String[ ] args)
	{
		int numErrors = 0;
		Patient p = new Patient("Pete", 123456);
		if (p.getBedLabel() != -1)
		{
			System.out.println("The bed label is " + p.getBedLabel() 
			                   + " when it should be -1");
			numErrors++;
		}
		if (p.doctors.size() != 0)
		{
			System.out.println("The patient should have no docotrs, "
			                   + "but already has the patients " + p.doctors);
			numErrors++;
		}
		p.setBedLabel(205);
		if (p.getBedLabel() != 205)
		{
			System.out.println("The bed label is " + p.getBedLabel() 
			                   + " when it should be 205");
			numErrors++;
		}
		p.setBedLabel(201);
		if (p.getBedLabel() != 201)
		{
			System.out.println("The bed label is " + p.getBedLabel() 
			                   + " when it should be 201");
			numErrors++;
		}
		p.release();
		if (p.getBedLabel() != -1)
		{
			System.out.println("The bed label is " + p.getBedLabel() 
			                   + " when it should be -1");
			numErrors++;
		}
		Doctor d = new Doctor("Joe");
		p.addDoctor(d);
		if (!p.hasDoctor("Joe"))
		{
			System.out.println("Either addDoctor or hasDoctor failed, " 
			                   + "as Pete does not have doctor Joe.");
			numErrors++;
		}
		d = new Doctor("Mary");
		p.addDoctor(d);
		if (!p.hasDoctor("Mary"))
		{
			System.out.println("Either addDoctor or hasDoctor failed, " 
			                   + "as Pete does not have doctor Mary.");
			numErrors++;
		}
		if (p.doctors.size() != 2)
		{
			System.out.println("The patient should have two docotrs, "
			                   + "but he has the patients " + p.doctors);
			numErrors++;
		}
		p.setBedLabel(204);
		System.out.println("The person called Pete with number 123456 in bed 204 " 
		                   + "and doctors Joe and Mary is " + p + "\n");
		
		p.removeDoctor("Mary");
		if (p.hasDoctor("Mary"))
		{
			System.out.println("Either removeDoctor or hasDoctor failed, " 
			                   + "as Pete still has doctor Mary.");
			numErrors++;
		}
		p.removeDoctor("Joe");
		if (p.hasDoctor("Joe"))
		{
			System.out.println("Either removeDoctor or hasDoctor failed, " 
			                   + "as Pete still has doctor Joe.");
			numErrors++;
		}
		if (p.doctors.size() != 0)
		{
			System.out.println("The patient should have no docotrs, "
			                   + "but still has the patients " + p.doctors);
			numErrors++;
		}
		System.out.println("The number of errors found is " + numErrors);
	}
}
