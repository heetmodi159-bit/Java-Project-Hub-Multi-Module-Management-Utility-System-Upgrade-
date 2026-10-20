package OS_System;

import java.util.Random;
import java.util.Scanner;

/** Game logic for the five activities shown in kidsMenu. */
public class KidsModule {

    static Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    private KidsModule() {
    }

    public static void numberGuess() {
        OSConsoleUI.printBanner("NUMBER GUESSING", "FIND THE SECRET NUMBER FROM 1 TO 100");
        int number = random.nextInt(100) + 1;
        int attempts = 7;

        for (int i = 1; i <= attempts; i++) {
            int guess = readInt("Attempt " + i + " of " + attempts + " - Enter your guess: ");
            if (guess == number) {
                OSConsoleUI.printBanner("YOU WIN", "Correct guess in " + i + " attempt(s)!");
                return;
            }
            if (guess < number) {
                System.out.println("Hint: Try a HIGHER number.");
            } else {
                System.out.println("Hint: Try a LOWER number.");
            }
        }

        OSConsoleUI.printBanner("ROUND COMPLETE", "The secret number was " + number + ".");
    }

    public static void mathQuiz() {
        OSConsoleUI.printBanner("MATH QUIZ", "SOLVE FIVE QUICK QUESTIONS");
        int score = 0;

        for (int i = 1; i <= 5; i++) {
            int first = random.nextInt(20) + 1;
            int second = random.nextInt(20) + 1;
            int operation = random.nextInt(3);
            int answer;
            String symbol;

            if (operation == 0) {
                answer = first + second;
                symbol = "+";
            } else if (operation == 1) {
                if (second > first) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
                answer = first - second;
                symbol = "-";
            } else {
                answer = first * second;
                symbol = "x";
            }

            System.out.println("Question " + i + " of 5");
            int userAnswer = readInt(first + " " + symbol + " " + second + " = ");
            if (userAnswer == answer) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Not quite. Correct answer: " + answer);
            }
        }

        OSConsoleUI.printBanner("QUIZ RESULT", "Your score: " + score + " / 5");
    }

    public static void rockPaperScissors() {
        OSConsoleUI.printBanner("ROCK PAPER SCISSORS", "CHOOSE YOUR MOVE");
        String[] moves = {"rock", "paper", "scissors"};
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        int choice = readInt("Your choice (1-3): ");

        if (choice < 1 || choice > 3) {
            System.out.println("Please choose 1, 2, or 3 next time.");
            return;
        }

        int computerChoice = random.nextInt(3) + 1;
        String playerMove = moves[choice - 1];
        String computerMove = moves[computerChoice - 1];
        System.out.println("You chose: " + playerMove);
        System.out.println("Computer chose: " + computerMove);

        if (choice == computerChoice) {
            OSConsoleUI.printBanner("RESULT", "It is a draw!");
        } else if ((choice == 1 && computerChoice == 3)
                || (choice == 2 && computerChoice == 1)
                || (choice == 3 && computerChoice == 2)) {
            OSConsoleUI.printBanner("RESULT", "You win this round!");
        } else {
            OSConsoleUI.printBanner("RESULT", "Computer wins this round.");
        }
    }

    public static void wordScramble() {
        OSConsoleUI.printBanner("WORD SCRAMBLE", "UNSCRAMBLE THE MIXED-UP WORD");
        String[] words = {"JAVA", "APPLE", "PLANET", "GARDEN", "PUZZLE", "KEYBOARD"};
        String word = words[random.nextInt(words.length)];
        String scrambled = scramble(word);
        int attempts = 3;

        System.out.println("Scrambled word: " + scrambled);
        for (int i = 1; i <= attempts; i++) {
            System.out.print("Your answer (attempt " + i + " of " + attempts + "): ");
            String answer = sc.nextLine().trim();
            if (answer.equalsIgnoreCase(word)) {
                OSConsoleUI.printBanner("WORD SOLVED", "Great job! You found " + word + ".");
                return;
            }
            System.out.println("Not yet. Try again!");
        }
        OSConsoleUI.printBanner("ROUND COMPLETE", "The word was " + word + ".");
    }

    public static void oddOneOut() {
        OSConsoleUI.printBanner("ODD ONE OUT", "PICK THE ITEM THAT DOES NOT BELONG");
        String[][] questions = {
                {"Cat", "Dog", "Cow", "Carrot", "4"},
                {"Circle", "Square", "Triangle", "Banana", "4"},
                {"Bus", "Train", "Bicycle", "Orange", "4"},
                {"January", "March", "Sunday", "July", "3"},
                {"Piano", "Guitar", "Drum", "Pillow", "4"}
        };
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            String[] question = questions[i];
            System.out.println("\nQuestion " + (i + 1) + " of " + questions.length);
            for (int option = 0; option < 4; option++) {
                System.out.println((option + 1) + ". " + question[option]);
            }
            int answer = readInt("Which item is different? (1-4): ");
            if (answer == Integer.parseInt(question[4])) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Try the next one!");
            }
        }

        OSConsoleUI.printBanner("GAME RESULT", "Your score: " + score + " / " + questions.length);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String scramble(String word) {
        char[] letters = word.toCharArray();
        for (int i = letters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }
        String result = new String(letters);
        if (result.equals(word) && letters.length > 1) {
            char temp = letters[0];
            letters[0] = letters[1];
            letters[1] = temp;
            result = new String(letters);
        }
        return result;
    }
}
