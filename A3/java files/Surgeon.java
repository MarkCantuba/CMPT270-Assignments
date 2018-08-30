// Mark Cantuba
// MJC862
// 11214496

/**
 * Surgeon class is a special kind of.0+
 * @author Mark
 */
public class Surgeon extends Doctor {
	
	/**
	 * Constructor class that creates an instance of the Surgeon class
	 * @param name: Surgeon's name
	 */
	public Surgeon(String name) {
		super(name);
	}

	@Override
	public String toString() {
		String patient = "";
		for (Patient x: patientList) {
			patient += x.getName() + "\n";
		}
		return "Surgeon's name: " + super.getName() + 
				"\n\n**Assigned Patients to Doctor " + super.getName() + "**\n" + patient;
		
		
	}
	public static void main(String[] args) {
		Surgeon test1 = new Surgeon("Goldman");
		Surgeon test2 = new Surgeon("Paul");
		if (!test1.getName().equals("Goldman") || !test2.getName().equals("Paul")) {
			System.out.println("test1 Surgeon should be Goldman and test2 name should be Paul. getName from person method might not "
					+ "be compatible with Surgeon class. Fix this!");
		}
		
		// Printing toString() method
		System.out.println("\n******************************************");
		System.out.println("**Displaying Surgeon's toString Method**\n");
		System.out.println("-test1-\n" + test1.toString());
		System.out.println("-test2-\n" + test2.toString());
		System.out.println("******************************************");
	}
}
