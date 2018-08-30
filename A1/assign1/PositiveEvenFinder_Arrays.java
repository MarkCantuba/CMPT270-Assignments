package assign1;

import java.util.Arrays;

public class PositiveEvenFinder_Arrays {

    public static void main(String[] args) {
        int[] inputs = {1, 2, 3, 4, 5};
        int[] result = findPositiveEvens(inputs);
        if (!Arrays.equals(result, new int[] {2,4})) {
            System.out.println("Testing fault: findPositiveEvens returned " +
                    Arrays.toString(result) + " on inputs " + Arrays.toString(inputs));
        }
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
