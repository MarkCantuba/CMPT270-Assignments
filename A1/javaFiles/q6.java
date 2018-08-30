import java.util.Arrays;
import java.util.ArrayList;

public class q6 {
	public static void main(String[] args) {
	int[] test = {1,2,3,4,5};
	int[] result = getEvens(test);
	int[] expected = {2,4};

	if (!Arrays.equals(result, expected)) {
		System.out.println("ERROR! WRONG OUTPUT! " + Arrays.toString(expected) + " Is the expected output. Got " +
				Arrays.toString(result) + " instead!");
		}
	}

	public static int[] getEvens(int[] anArray) {
		ArrayList<Integer> evens = new ArrayList<Integer>();
		int counter = 0;
		for (int nums : anArray) {
			if (nums % 2 == 0) {
				evens.add(nums);

				}
			}

		int[] newList = new int[evens.size()];
		for (int even : evens) {
			newList[counter] = even;
			counter++;
		}
		return newList;
	}
}
