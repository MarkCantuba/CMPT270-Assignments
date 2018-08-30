public class q1 {

	/**
	 * This is the main function. It basically just stores the average of the given array, numberArray[], into
	 * the double variable avgValue, and is printed out into the console.
	 */
	public static void main(String[] args) {
		double numberArray[] = {1, 3, 4, 5};
		double avgValue = average(numberArray);
		System.out.println("Average = " + avgValue);
		}

	 /** This methods takes each element of a double[] array, an_array, and average all values up.
	 * @param an_array: an array consisting of double values
	 * @return the average of all elements within the array, in variable type double
	 */
	public static double average(double[] an_array) {
		double total = 0;

		for (int i = 0; i < an_array.length; i++) {
			total = total + an_array[i];
		}
		double average = total / an_array.length;
		return average;
	}

}
