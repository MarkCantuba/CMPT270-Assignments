//Mark Cantuba
//MJC862
//11214496
/**
 * Ward class is the main class, that serves as a container for all the patients created by the Person class. Each person can be stored/removed
 * in or out an any given point in time. An empty ward is represented by just a bed label, and an occupied ward is represented by bedlabel:patient
 * pairs.
 * @author Mark
 *
 */
public class Ward {
	/**
	 * nameOfWard: this instance stores the name of the ward. cannot be changed
	 * firstBed: stores the number of the very first bed of the ward. Sort of like rm.1
	 * bedWard: Container instance, where all the patients are stored. null if empty by default.
	 */
	private String nameOfWard;
	private int firstBed;
	private Person[] bedWard;
	
	/**
	 * Constructor class that creates an instance of the Ward class
	 * @param wardName: Name of the ward
	 * @param min: the integer label of the first bed within the ward..
	 * @param max: the largest integer for a bed label
	 */
	public Ward(String wardName, int min, int max) {
		nameOfWard = wardName;
		firstBed = min;
		bedWard = new Person[(max+1) - min];
	}
	
	
	/**
	 * Getter method to get the name of the ward.
	 * @return: name of ward
	 */
	public String getNameOfWard() {
		return nameOfWard;
	}
	
	
	/**
	 * Getter method to get the number of the first bed label.
	 * @return: the number of the first bed.
	 */
	public int getFirstBed() {
		return firstBed;
	}
	
	
	/**
	 * Getter method to access the largest integer number label of a bed.
	 * @return number of the last bed.
	 */
	public int getLastBed() {
		return bedWard.length + firstBed - 1;
	}
	
	
	/**
	 * Convert the indexNumber given by the user, into external bed label number
	 * @param indexNumber: index number of the bed the user is searching for
	 * @return: the integer equivalent of the external bed label
	 */
	public int toBedLabel(int indexNumber) {
		int index = firstBed + indexNumber;
		return index;
	}
	

	/**
	 * Converts the given bed label into index number, assuming that the bed numbers are in consecutive order, increasing by 1.
	 * @param labelNumber: bed's external label number
	 * @return external bed label's  equivalent index number
	 */
	public int toIndex(int labelNumber) {		
		int labelNum = labelNumber - firstBed;
		return labelNum;
	}
	
	
	/**
	 * Determine if the bed is occupied or not
	 * @param bedLabel: bed number of the ward user wants to check
	 * @return: boolean true if the ward is occupied, otherwise boolean false if ward is empty.
	 */
	public boolean isOccupied(int bedLabel) {
		return bedWard[toIndex(bedLabel)] != null;
	}
	
	
	/**
	 * get the name of the person assigned to bed ward 'bedLabel,' assuming that the given bed label is valid
	 * We assume that the user will give a valid bedLabel number
	 * @param bedLabel: bed number of the patient user wants to check
	 * @return: name of the person inside the ward.
	 */
	public String getPerson(int bedLabel) {
		return bedWard[toIndex(bedLabel)].getPatientName();
	}
	
	
	/**
	 * Assigns a person to a specific bed, given a specific integer bed label. Assign to null to re-empty bed.
	 * @param patientName: name of the patient obtained from Person class
	 * @param bedNumber: number of the bed you wish to store the person in (not index!!)
	 */
	public void assignPerson(Person patient, int bedNumber) {
		if (isOccupied(bedNumber)) {
			System.out.println("Bed is occupied!");
		}
		bedWard[toIndex(bedNumber)] = patient;
	}
	
	
	/**
	 * method that displays a string representation of the info regarding the ward. Name will be included if ward is not
	 * empty, otherwise will just print out the room number.
	 */
	public String toString() {
		String test = "";
		int counter = 0;
		for (Person i : bedWard) {
			if (i == null) {
				test = test + Integer.toString(toBedLabel(counter)) + "\n";
			}
			else {
				test = test + Integer.toString(toBedLabel(counter))+ ": " +  getPerson(toBedLabel(counter)) + "\n";
			}
			counter++;
		}
		
		return "***" + nameOfWard + "***\n" + test;
			}
		
	
	/*
	 * Testing Ward class.
	 * nothing will be printed out if all test passed, except for the toString() method.
	 * toString() method will be displayed, to show all unoccupied and occupied wards.
	 * nothing else should be printed out besides the toString method if everything works!
	 */
	public static void main(String[] args) {

		Ward tester = new Ward("Royal University Hospital", 200, 250);
		Person patient1 = new Person("Someguy in a trench Coat", 010101010);
		Person patient2 = new Person("Bob", 115113222);
		Person patient3 = new Person("Herbert", 987522454);
		Person patient4 = new Person("Pickle Rick", 516454257);
		Person patient5 = new Person("Morty", 948455632);
		String out1 = "Someguy in a trench Coat";
		String out2 = "Bob";
		String out3 = "Herbert";
		String out4 = "Pickle Rick";
		String out5 = "Morty";

		
		String expectedName = "Royal University Hospital";
		
		// testing .getNameOfWard method
		if (!expectedName.equals(tester.getNameOfWard())) {
			System.out.println("The expected name is " + expectedName + ". But got " + tester.getNameOfWard() + " instead. Fix"
					+ " .getNameOfWard() method!");
		}
		
		tester.assignPerson(patient1, 200);
		tester.assignPerson(patient2, 210);
		tester.assignPerson(patient3, 214);
		tester.assignPerson(patient4, 225);
		tester.assignPerson(patient5, 250);
		
		// testing .getPerson and .assignPerson method and Person class.
		if (!out1.equals(tester.getPerson(200))) {
			System.out.println("The expected name is " + out1 + ". But got " + tester.getPerson(200) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!out2.equals(tester.getPerson(210))) {
			System.out.println("The expected name is " + out2 + ". But got " + tester.getPerson(210) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!out3.equals(tester.getPerson(214))) {
			System.out.println("The expected name is " + out3 + ". But got " + tester.getPerson(214) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!out4.equals(tester.getPerson(225))) {
			System.out.println("The expected name is " + out4 + ". But got " + tester.getPerson(225) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!out5.equals(tester.getPerson(250))) {
			System.out.println("The expected name is " + out5 + ". But got " + tester.getPerson(250) + " instead. Check "
					+ " .getPerson() and .assignPerson() method!");
		}
		
		
		// testing .toIndex method
		if (tester.toIndex(200) != 0) {
			System.out.println("The expected index of 200 is 0. But, got " + tester.toIndex(200) + " insted. Check .toIndex method.");
		}
		if (tester.toIndex(210) != 10) {
			System.out.println("The expected index of 200 is 10. But, got " + tester.toIndex(210) + " insted. Check .toIndex method.");
		}
		if (tester.toIndex(214) != 14) {
			System.out.println("The expected index of 200 is 14. But, got " + tester.toIndex(214) + " insted. Check .toIndex method.");
		}
		if (tester.toIndex(225) != 25) {
			System.out.println("The expected index of 200 is 25. But, got " + tester.toIndex(225) + " insted. Check .toIndex method.");
		}
		if (tester.toIndex(250) != 50) {
			System.out.println("The expected index of 200 is 50. But, got " + tester.toIndex(250) + " insted. Check .toIndex method.");
		}
		
		// testing .toBedLabel method
		if (tester.toBedLabel(0) != 200) {
			System.out.println("The expected label of index 0 is 200. But got " + tester.toBedLabel(0) + " instead. Check .toBedLabel method!");
		}
		if (tester.toBedLabel(10) != 210) {
			System.out.println("The expected label of index 10 is 210. But got " + tester.toBedLabel(10) + " instead. Check .toBedLabel method!");
		}
		if (tester.toBedLabel(14) != 214) {
			System.out.println("The expected label of index 14 is 214. But got " + tester.toBedLabel(14) + " instead. Check .toBedLabel method!");
		}
		if (tester.toBedLabel(25) != 225) {
			System.out.println("The expected label of index 25 is 225. But got " + tester.toBedLabel(25) + " instead. Check .toBedLabel method!");
		}
		if (tester.toBedLabel(50) != 250) {
			System.out.println("The expected label of index 50 is 250. But got " + tester.toBedLabel(50) + " instead. Check .toBedLabel method!");
		}
		
		// testing .getFirstBed method
		if (tester.getFirstBed() != 200) {
			System.out.println("The expected first bed is bed label 200. But got " + tester.getFirstBed() + " instead. Check .getFirstBed method!");
		}
		
		// testing .getLastBed method
		if (tester.getLastBed() != 250) {
			System.out.println("The expected last bed is bed label 250. But got " + tester.getLastBed() + " instead. Check .getLastBed method!");
		}
		
		// testing .isOccupied method
		if (tester.isOccupied(200) != true) {
			System.out.println("Bed 200 should be occupied, and must return true. Got " + tester.isOccupied(200) + "instead. Check .isOccupied method!");
		}
		if (tester.isOccupied(201) != false) {
			System.out.println("Bed 201 should not be occupied, and must return false. Got " + tester.isOccupied(201) + "instead. Check .isOccupied method!");
		}
		if (tester.isOccupied(249) != false) {
			System.out.println("Bed 249 should not be occupied, and must return true. Got " + tester.isOccupied(249) + "instead. Check .isOccupied method!");
		}
		if (tester.isOccupied(250) != true) {
			System.out.println("Bed 250 should be occupied, and must return true. Got " + tester.isOccupied(250) + "instead. Check .isOccupied method!");
		}
		if (tester.isOccupied(230) != false) {
			System.out.println("Bed 230 should not be occupied, and must return false. Got " + tester.isOccupied(230) + "instead. Check .isOccupied method!");
		}
		
		patient1.changePatientName("Herald");
		patient2.changePatientName("Derek");
		patient3.changePatientName("Cole");
		patient4.changePatientName("Josh");
		patient5.changePatientName("Harley");
		
		// testing .changePersonMethod() from Person, to check the effect to the array.
		if (!"Herald".equals(tester.getPerson(200))) {
			System.out.println("The expected name is " + out1 + ". But got " + tester.getPerson(200) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!"Derek".equals(tester.getPerson(210))) {
			System.out.println("The expected name is " + out2 + ". But got " + tester.getPerson(210) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!"Cole".equals(tester.getPerson(214))) {
			System.out.println("The expected name is " + out3 + ". But got " + tester.getPerson(214) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!"Josh".equals(tester.getPerson(225))) {
			System.out.println("The expected name is " + out4 + ". But got " + tester.getPerson(225) + " instead. Fix "
					+ " .getPerson() and .assignPerson() method!");
		}
		if (!"Harley".equals(tester.getPerson(250))) {
			System.out.println("The expected name is " + out5 + ". But got " + tester.getPerson(250) + " instead. Check "
					+ " .getPerson() and .assignPerson() method!");
		}
		
	
		// showing toString() methods of Person and Ward.
		System.out.println("***Displaying toString() method of Person Class*** \n");
		System.out.println(patient1.toString());
		System.out.println(patient2.toString());
		System.out.println(patient3.toString());
		System.out.println(patient4.toString());
		System.out.println(patient5.toString());
		System.out.println("\n***Displaying toString() method of Ward Class*** \n");
		System.out.println(tester.toString());
		
	}

}
