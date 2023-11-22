package star.odyssey.command;

import java.util.Scanner;

public class CommandReader {
    private Scanner scanner;

    public CommandReader() {
        // Initialize the scanner for reading user input
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        // Prompt the user and read the command line input
        System.out.print(">");
        return scanner.nextLine();
    }
}
