import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        char playAgain;

        System.out.println("🎮 Welcome to the Number Guessing Game!");

        do {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nI have generated a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = sc.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("🎉 Correct! You guessed the number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("📉 Too high!");
                } else {
                    System.out.println("📈 Too low!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("❌ You've used all attempts.");
                System.out.println("The correct number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play again? (y/n): ");
            playAgain = sc.next().toLowerCase().charAt(0);

        } while (playAgain == 'y');

        System.out.println("\n🏆 Game Over!");
        System.out.println("Your final score (rounds won): " + score);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}

