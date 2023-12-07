package star.odyssey.main;

import star.odyssey.ui.MainMenu;
import star.odyssey.ui.swing.MainFrame;

import static star.odyssey.ui.DisplaySplash.displaySplash;

// Main class to run the Star Odyssey game.
public class Main {


    public static void main(String[] args) {
        // Swing UI:
        MainFrame mainFrame = new MainFrame();

        // Console UI:
        displaySplash(); // Displaying the splash screen at startup.
        MainMenu.execute();
        // Starting the client execution.
    }
}