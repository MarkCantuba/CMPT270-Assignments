package assign1;

public class ArrayAverager {

    public static void main(String[] args) {
        double numberArray[] = {1, 3, 4, 5};
        double avgValue = average(numberArray);
        System.out.println("average = " + avgValue);
    }

    public static double average(double[] numberList) {
        double total = 0;
        for (int i = 0; i < numberList.length; i++) {
            total += numberList[i];
        }
        return total / numberList.length;
    }

}
