import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner sc = new Scanner(System.in);

        int number = random.nextInt(100) + 1;
        int attempts = 5;

        System.out.println("Guess a number between 1 and 100");

        while (attempts > 0) {
            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();

            if (guess == number) {
                System.out.println("Correct!");
                break;
            } else if (guess > number) {
                System.out.println("Too high");
            } else {
                System.out.println("Too low");
            }

            attempts--;
        }

        sc.close();
    }
}
