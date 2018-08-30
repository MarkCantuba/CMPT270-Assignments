import java.util.Scanner;

public class q3 {
	public static void main(String[] args) {
		String username = introductions("Welcome to my Java Program!");
		System.out.println("Got username " + username);
	}

	public static String introductions(String greeting) {
		Scanner sc = new Scanner(System.in);
		System.out.println(greeting);

		System.out.print("Please enter your name: ");
		String name = sc.nextLine();

		System.out.println("Hello, " + name);

		return name;
	}
}
