package assign1;

import java.util.Scanner;

public class NumberGuesser {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int guess;
        do {
            System.out.print("Guess a number between 1 and 100: ");
            guess = in.nextInt();
            if (guess < 1) {
                System.out.println("Too low!");
            } else if (guess > 100) {
                System.out.println("Too high!");
            }
        } while (guess < 1 || guess > 100);
        System.out.print("That was a great guess!");
    }

}
