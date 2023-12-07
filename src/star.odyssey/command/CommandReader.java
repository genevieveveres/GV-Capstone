package star.odyssey.command;

import java.util.Scanner;

public class CommandReader {
    private final Scanner scanner;

    public CommandReader() {
        // Initialize the scanner for reading user input
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String input;
        do {
            // Prompt the user and read the command line input
            System.out.print(">");
            input = scanner.nextLine();
        } while (input.trim().isEmpty()); // Keep prompting if input is empty

        return input;
    }
}
