package star.odyssey.main;

import star.odyssey.ui.MainMenu;

// Main class to run the Star Odyssey game.
public class Main {
    public static void main(String[] args) {
        MainMenu controller = new MainMenu(); // Creating the game client.
        controller.execute();
        // Starting the client execution.
    }
}