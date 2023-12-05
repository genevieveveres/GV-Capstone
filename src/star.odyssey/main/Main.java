package star.odyssey.main;

import star.odyssey.ui.MainMenu;

import static star.odyssey.ui.DisplaySplash.displaySplash;

// Main class to run the Star Odyssey game.
public class Main {


    public static void main(String[] args) {
        displaySplash(); // Displaying the splash screen at startup.
        MainMenu.execute();
        // Starting the client execution.
    }
}