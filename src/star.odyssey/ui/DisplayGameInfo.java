package star.odyssey.ui;

import java.io.FileReader;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class DisplayGameInfo {

    public static void displayGameInfo() {
        String file = "./data/gameInfo.txt";
        clearScreen();
        try {
            FileReader fileReader = new FileReader(file);
            int i;
            while ((i = fileReader.read()) != -1) {
                System.out.print((char) i);
            }
            pauseDisplay();
            clearScreen();
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }
}