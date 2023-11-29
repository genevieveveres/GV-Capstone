package star.odyssey.ui;

import org.apache.commons.text.WordUtils;

import java.util.Scanner;

public class ConsoleDisplayUtils {

    // clears screen
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For non-Windows systems, you can use the ANSI escape code
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., file not found, JSON parsing error)
            e.printStackTrace();
        }
    }

    // Displays text input in red
    public static String makeRed(String txt) {
        return "\u001B[31m" + txt + "\u001B[0m";
    }

    // Displays text input in blue
    public static String makeBlue(String txt) {
        return "\u001B[34m" + txt + "\u001B[0m";
    }

    public static String makeCyan(String txt) {
        return "\u001B[36m" + txt + "\u001B[0m";
    }

    public static String makeMagenta(String txt) {
        return "\u001B[95m" + txt + "\u001B[0m";
    }

    public static String makeBackgroundGreen(String txt) {
        return "\033[42m" + txt + "\033[0m";
    }

    public static String wrapText(String txt) {
        return WordUtils.wrap(txt, 100);
    }

    public static void printDivider() {
        printDivider(100, "");
    }

    public static void printDivider(String title) {
        printDivider(100, title);
    }

    public static void printDivider(int totalLength, String title) {
        int remainingEquals = Math.max(0, totalLength - title.length());

        System.out.println(makeRed("==" + makeCyan(title) + makeRed(repeatCharacter('=', remainingEquals))));
    }

    private static String repeatCharacter(char character, int count) {
        StringBuilder result = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            result.append(character);
        }
        return result.toString();
    }


    // Centers text on console
    public static String centerText(int width, String text) {
        int textLength = text.length();
        int padding = (width - textLength) / 2;

        StringBuilder centeredText = new StringBuilder();

        for (int i = 0; i < padding; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);

        return centeredText.toString();
    }

    // pauses display output until user presses the Enter key
    public static void pauseDisplay() {
        String message = makeRed("Press \"ENTER\" to continue...");
        System.out.println("\n");
        System.out.println(centerText(100, message));
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) {
        System.out.println(centerText(20, "text"));
    }
}