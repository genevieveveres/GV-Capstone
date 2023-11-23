package star.odyssey.game;

import com.apps.util.Prompter;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    Prompter prompter = new Prompter(new Scanner(System.in)); // Utility for user prompts.

    public void execute() throws IOException {
        String file = "./data/splashScreen.txt";
        DisplaySplash.displaySplash(file); // Displaying the splash screen at startup.

        int option;
        do {
            System.out.println("\nPlease select an option:");
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit");
            // User selection for game options.
            option = Integer.parseInt(prompter.prompt("\nMake a selection (1-3): ", "[123]", "\nInvalid choice. Please select 1,2 or 3."));
            switch (option) {
                case 1:
                    Game game = new Game();
                    game.start(); // Starting a new game.
                    break;
                case 2:
                    System.out.println("Load Game");
                    break;
                case 3:
                    System.out.println("Exiting..."); // Exiting the application.
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option < 1 || option > 3);
    }
}
