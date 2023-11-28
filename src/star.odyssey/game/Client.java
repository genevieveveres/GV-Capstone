package star.odyssey.game;

import com.apps.util.Prompter;
import star.odyssey.ui.DisplayBackstory;
import star.odyssey.ui.DisplayGameInfo;
import star.odyssey.ui.DisplaySplash;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    Prompter prompter = new Prompter(new Scanner(System.in)); // Utility for user prompts.

    public void execute() throws IOException {
        DisplaySplash.displaySplash(); // Displaying the splash screen at startup.

        int option;
        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. New Game");
            System.out.println("2. Load Game <Future feature in next release>");
            System.out.println("3. Display Backstory and Basic Game Information");
            System.out.println("4. Exit");
            // User selection for game options.
            option = Integer.parseInt(prompter.prompt("\nMake a selection (1-4): ", "[1234]", "\nInvalid choice. Please select 1,2,3 or 4."));
            switch (option) {
                case 1:
                    GameManager gameManager = new GameManager();
                    DisplayBackstory.displayBackstory();
                    DisplayGameInfo.displayGameInfo();
                    gameManager.startGame(); // Starting a new game.
                    break;
                case 2:
                    System.out.println("Load Game");
                    break;
                case 3:
                    DisplayBackstory.displayBackstory();
                    DisplayGameInfo.displayGameInfo();
                    option = 0;
                    continue;
                case 4:
                    System.out.println("Exiting..."); // Exiting the application.
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option < 1 || option > 4);
    }
}
