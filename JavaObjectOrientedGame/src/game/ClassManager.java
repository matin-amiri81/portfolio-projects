package game;
import game.actors.classes.Bandit;
import game.actors.classes.Samurai;
import game.actors.classes.Wrench;
import game.actors.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that manages the player's class.
 * @author danny leung
 * @see Player
 * @see Bandit
 * @see Samurai
 * @see Wrench
 */
public class ClassManager {
    public static Player choosePlayerClass() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Choose your class:");
            System.out.println("1. Bandit");
            System.out.println("2. Samurai");
            System.out.println("3. Wrench");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 3) {
                    System.out.println("Maidenless. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Maidenless. Please enter a valid number.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice < 1 || choice > 3);

        Player player = null;

        switch (choice) {
            case 1:
                player = new Bandit();
                System.out.println("You have chosen the Bandit class.");
                break;
            case 2:
                player = new Samurai();
                System.out.println("You have chosen the Samurai class.");
                break;
            case 3:
                player = new Wrench();
                System.out.println("You have chosen the Wrench class.");
                break;
        }

        return player;
    }
}
