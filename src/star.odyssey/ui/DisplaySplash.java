package star.odyssey.ui;

import java.io.FileReader;
import java.io.IOException;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class DisplaySplash {

    // Display splash screen
    public static void displaySplash() throws IOException {
        clearScreen();
        String file = "./data/splashScreen.txt";
        try {
            FileReader fileReader = new FileReader(file);

            int i;
            while ((i = fileReader.read()) != -1) {
                if ((char) i == '█') {
                    System.out.print(makeRed(String.valueOf((char) i)));
                } else if ((char) i == '░') {
                    System.out.print(makeBlue(String.valueOf((char) i)));
                } else {
                    System.out.print((char) i);
                }
            }
            pauseDisplay();
            clearScreen();
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "./data/splashScreen.txt";
        displaySplash();
    }
}
