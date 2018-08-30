package entities;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * A doctor with a unique name and a list of patients.
 */
public class Doctor extends BasicDoctor 
{
	/**
	 * The list of all patients of the doctor.
	 */
	protected LinkedList<Patient> patients;

	/**
	 * Initialize an instance with the given name.
	 * @param name     the name for the doctor
	 * @precond name != null && !name.equals("")
	 */
	public Doctor(String name)
	{
		super(name);
		patients = new LinkedList<Patient>();
	}

	/**
	 * Add a patient to the list of patients for this doctor
	 * @param p the patient to be added to the doctor's list
	 * @precond ! hasPatient(p.getHealthNumber())
	 */
	public void addPatient(Patient p)
	{
		if (hasPatient(p.getHealthNumber()))
			throw new RuntimeException("Patient " + p.getHealthNumber() 
			                           + " is already a patient of " + this);
		patients.add(p);
	}

	/**
	 * Is the Patient with the specified health number a patient of this doctor?
	 * @param healthNum  the health number of the Patient to be tested 
	 *                   for being a patient of this doctor
	 * @return is the Patient with the specified health number a patient of this doctor?
	 */
	public boolean hasPatient(int healthNum)
	{
		Iterator<Patient> iter = patients.iterator();
		while (iter.hasNext())
		{
			Patient p = iter.next();
			if (p.getHealthNumber() == healthNum)
				return true;
		}
		return false;
	}

	/**
	 * Remove the patient with the specified health number 
	 * from the list of those being treated by this doctor
	 * @param healthNum  the health number of a Patient
	 * @precond  hasPatient(healthNum)

	 */
	public void removePatient(int healthNum)
	{
		Iterator<Patient> iter = patients.iterator();
		while (iter.hasNext())
		{
			Patient p = iter.next();
			if (p.getHealthNumber() == healthNum)
			{
				iter.remove();
				return;
			}
		}
		throw new RuntimeException("Doctor " + getName()
		                + " does not have a patient with health number " + healthNum);
	}

	/**
	 * Return a string representation of the doctor
	 * @return a string representation of the doctor
	 */
	public String toString()
	{
		String result = super.toString() + "with patients ";
		for (Patient p: patients)
			result = result + " " + p.getHealthNumber();
		return result + "\n";
	}

	/**
	 * Carry out basic tests of this class
	 */
	public static void main(String[ ] args)
	{
		Doctor d = new Doctor("Mary");
		System.out.println("The doctor with the name Mary and no patients is " + d);
		if (!d.getName().equals("Mary"))
			System.out.println("The doctor has name " + d.getName() + " rather than Mary");
		if (d.patients.size() != 0)
			System.out.println("The doctor should have no patients, "
			                   + "but already has the patients " + d.patients);
		Patient p = new Patient("Bill", 123);
		d.addPatient(p);
		if (!d.hasPatient(p.getHealthNumber()))
			System.out.println("The doctor should have a patient with health number 123, "
			                   + "but the patient's list is " + d.patients);
		p = new Patient("Linda", 321);
		d.addPatient(p);
		System.out.println("The doctor with the name Mary and patients with health" 
		                   + " numbers 123 and 321 is " + d);
		d.removePatient(p.getHealthNumber());
		if (d.hasPatient(p.getHealthNumber()))
			System.out.println("The doctor should not have a patient with health number 123, "
			                   + "but the patient's list is " + d.patients);
	}
}
