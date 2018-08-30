import java.util.Scanner;

public class q5 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Guess a number between 1 and 100: ");
		int guess = sc.nextInt();


		do
		{
			if (guess < 1)
			{
				System.out.println("Too Low!");
				System.out.print("Please enter another number: ");
				guess = sc.nextInt();
			}

			else if (guess > 100)
			{
				System.out.println("Too High!");

				System.out.print("Please enter another number: ");
				guess = sc.nextInt();
			}
		} while (guess < 1 || guess > 100);

		System.out.println("That was a valid guess!");
	}

}
