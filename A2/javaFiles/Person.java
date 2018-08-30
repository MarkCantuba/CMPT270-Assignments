//Mark Cantuba
//MJC862
//11214496
/**
 * This is the Person class. This creates an instance that contains a patient's information, which will be used as a database in
 * the future. Comes in patientName:healthNumber pairs
 * @author Mark
 *
 */
public class Person {
	/**
	 * patientName instance: Stores the name of the patient in String format. can be changed
	 * healthNumber: stores the patient's health number. Cannot be changed.
	 */
	private String patientName;
	private final int healthNum;
	
	/**
	 * Constructor function for the Person class. Creates a person object containing the patient's name and health Number!
	 * @param name patient's name. The name gets stored in the personName field. Can be mutated.
	 * @param healthNumber patient's health number. This gets stored in the healthNum field, and cannot be changed.
	 */
	public Person(String name, int healthNumber) {
		this.healthNum = healthNumber;
		this.patientName = name;
	}
	
	/**
	 * Accessor function to get the private personName field
	 * @return the patient's name
	 */
	public String getPatientName() {
		return this.patientName;
	}
	
	/**
	 * Accessor function to get the private healthNum field
	 * @return the patient's health Number
	 */
	public int gethealthNum() {
		return this.healthNum;
	}
	
	/**
	 * Mutator function that is used to change the name stored, in case of spelling errors or other corrections.
	 * @param newName The new name that is to replace the name stored in personName.
	 */
	public void changePatientName(String newName) {
		this.patientName = newName;
	}
	
	/**
	 * this method returns a string, containing the patient's full information, in readable format.
	 */
	public String toString() {
		return "Patient Name: " + this.patientName + " | Health Number: " + this.healthNum; 
	}
	
	/**
	 * main function containing test cases for the Person class. Will output an error message if a method is not working properly, otherwise
	 * nothing is printed out!
	 */
	public static void main(String[] args) {
		Person test = new Person("Mark Cantuba", 11214496);
		String expectedName = "Mark Cantuba";
		int expectedHealthNum = 11214496;
		String nameChange = "Wrong Name :P";

		if (!test.getPatientName().equals(expectedName)) {
			System.out.println("1) Test Failed for .getPersonName! The expected output is: " + expectedName + ". But got " 
		+ test.getPatientName() + " instead!");
		}
		
		if (expectedHealthNum != test.gethealthNum()) {
			System.out.println("2) Test Failed for gethealthNum! The expected output is: " + expectedHealthNum + ". But got " 
		+ test.gethealthNum() + " instead!");
		}

		
		test.changePatientName("Wrong Name :P");
		if (!test.getPatientName().equals(nameChange)) {
			System.out.println("3) The name has been change to: " + nameChange + ". But got " + test.getPatientName() + " instead!");
		}

		if (!test.toString().equals("Patient Name: Wrong Name :P | Health Number: 11214496")) {
			System.out.println("4) The .toString() Method should return: 'Patient Name: Wrong Name :P | Health Number: 11214496'" 
		+ ". But got '" + test.toString() + "' instead!");
		}
		
	}

}
