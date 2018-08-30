//Mark Cantuba
//MJC862
//11214496
/**
 * BasicDoctor class simply stores the name of the Doctor, that will be assigned to a patient in the future
 * @author Mark
 *
 */
public class BasicDoctor {
	/**
	 * Drname: Stores the name of the doctor in string format. Can be mutated
	 */
	private String DrName;
	
	/**
	 * Constructor method, that creates an instance of the BasicDoctor class.
	 * @param DocName: name of the doctor
	 */
	public BasicDoctor(String DocName) {
		DrName = DocName;
	}

	
	/**
	 * Getter method that accesses the name of the doctor stored in DrName field.
	 * @return: Name of the doctor/
	 */
	public String getDrName() {
		return DrName;
	}

	
	/**
	 * Changes the name of the doctor
	 * @param drName: Name that the user wish to change DrName into
	 */
	public void setDrName(String drName) {
		DrName = drName;
	}
	
	
	/**
	 * Displays the current stored name of the the doctor as a readable string.
	 */
	public String toString() {
		return "Doctor's Name: " + DrName; 
	}

	
	/**
	 * Main method to test Basic Doctor class. Nothing will be output when everything works!
	 */
	public static void main(String[] args) {
		BasicDoctor test = new BasicDoctor("Dr. Who");
		String expected = "Dr. Who";
		
		if (!expected.equals(test.getDrName())) {
			System.out.println("1) The expected output is " + expected + ", but got " + test.getDrName() + " instead!");
		}
		
		String expected2 = "Dr. What";
		test.setDrName("Dr. What");
		
		if (!expected2.equals(test.getDrName())) {
			System.out.println("2) The expected output is " + expected + ", but got " + test.getDrName() + " instead!");
		}

	}

}
