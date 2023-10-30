import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int userGuess = 0;

        System.out.println("Welcome to the Number Guessing Game! Try to guess the number between 1 and 100.");

        while (userGuess != randomNumber) {
            System.out.println("Enter your guess:");
            userGuess = scanner.nextInt();
            attempts++;

            if (userGuess > randomNumber) {
                System.out.println("Higher! Try again.");
            } else if (userGuess < randomNumber) {
                System.out.println("Lower! Try again.");
            }
        }

        System.out.println("Congratulations! You guessed the correct number: " + randomNumber + " in " + attempts + " attempts.");
    }
}