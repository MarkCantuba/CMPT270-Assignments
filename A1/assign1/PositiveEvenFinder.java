package assign1;

import java.util.Arrays;

public class PositiveEvenFinder {

    public static void main(String[] args) {
        int[] inputs = {1, 2, 3, 4, 5};
        int[] result = findPositiveEvens(inputs);
        if (result.length != 2 || result[0] != 2 || result[1] != 4) {
            System.out.println("Testing fault: findPositiveEvens returned " +
                    toString(result) + " on inputs " + toString(inputs));
        }
    }

    public static String toString(int[] array) {
        String str = "[ ";
        for (int i = 0; i < array.length; i++) {
            str += array[i] + " ";
        }
        return str + "]";
    }

    public static int[] findPositiveEvens(int[] numbers) {
        int[] tmp = new int[numbers.length];
        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > 0 && numbers[i] % 2 == 0) {
                tmp[count] = numbers[i];
                count++;
            }
        }

        int[] poseven = Arrays.copyOf(tmp, count);
        return poseven;
    }

}
