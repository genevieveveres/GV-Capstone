package star.odyssey.game;

import java.io.FileReader;
import java.io.IOException;

public class DisplaySplash {
    // Displays text input in red

    // clear screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String makeRed(String txt) {
        return "\u001B[31m" + txt + "\u001B[0m";
    }

    // Displays text input in blue
    public static String makeBlue(String txt) {
        return "\u001B[34m" + txt + "\u001B[0m";
    }

    // Display splash screen
    public static void displaySplash(String file) throws IOException {
        clearScreen();
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
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "./data/splashScreen.txt";
        displaySplash(file);
    }
}
