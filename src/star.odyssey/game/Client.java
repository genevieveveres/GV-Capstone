package star.odyssey.game;

import com.apps.util.Prompter;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    Prompter prompter = new Prompter(new Scanner(System.in));

    public void execute() throws IOException {
        String file = "./data/splashScreen.txt";
        DisplaySplash.displaySplash(file);

        int option;
        do {
            System.out.println();
            System.out.println("Please select an option:");
            System.out.println();
            System.out.println("1. New Game");
            System.out.println("2. Load Game");
            System.out.println("3. Exit");
            System.out.println();
            option = Integer.parseInt(prompter.prompt("\nMake a selection (1-3): ", "[123]", "\nInvalid choice. Please select 1,2 or 3."));
            switch (option) {
                case 1:
                    Game game = new Game();
                    game.start();
                    break;
                case 2:
                    System.out.println("Load Game");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (option < 1 || option > 3);
    }
}