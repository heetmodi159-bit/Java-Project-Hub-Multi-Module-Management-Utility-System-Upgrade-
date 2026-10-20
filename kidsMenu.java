
package OS_System;

import java.util.Scanner;

public class kidsMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        kidsMenu(sc);
        sc.close();
    }

    public static void kidsMenu(Scanner sc) {
        KidsModule.sc = sc;

        boolean running = true;

        while (running) {
            OSConsoleUI.printSquareMenu(
                    "KIDS GAMES",
                    "PLAY  |  THINK  |  LEARN",
                    new String[]{
                            "1. Number Guessing",
                            "2. Math Quiz",
                            "3. Rock Paper Scissors",
                            "4. Word Scramble",
                            "5. Odd One Out",
                            "6. Back to Main Menu"
                    }
            );

            System.out.print("Enter your choice (1-6): ");

            String input = sc.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Oops! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    KidsModule.numberGuess();
                    break;

                case 2:
                    KidsModule.mathQuiz();
                    break;

                case 3:
                    KidsModule.rockPaperScissors();
                    break;

                case 4:
                    KidsModule.wordScramble();
                    break;

                case 5:
                    KidsModule.oddOneOut();
                    break;

                case 6:
                    System.out.println("Going back to Main Menu...");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Oops! Please choose a number from 1 to 6."
                    );
            }
        }
    }
}
