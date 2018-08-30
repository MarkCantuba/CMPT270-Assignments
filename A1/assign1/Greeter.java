package assign1;

import java.util.Scanner;

public class Greeter {

    public static void main(String[] args) {
        String username = introductions("Welcome to my Java program!");
        System.out.println("got username "+username);
    }

    public static String introductions(String greeting) {
        System.out.println(greeting);
        System.out.print("Please enter your name: ");
        Scanner in = new java.util.Scanner(System.in);
        String name = in.next();
        System.out.println("Hello, " + name);
        return name;
    }

}
