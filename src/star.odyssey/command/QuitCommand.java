package star.odyssey.command;

import java.util.Scanner;

public class QuitCommand implements Command {
    private final Scanner scanner;

    public QuitCommand() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute(String noun) {
        System.out.print("Thinking of warping back to reality? (yes/no)\n>");
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            System.out.println("Rocketing back to normalcy! Remember, Earth has no glowing trees or melody mountains");
            System.exit(0);
        } else if ("no".equals(response)) {
            System.out.println("That’s the spirit! Who needs Earth when you’ve got an alien world to explore?");
        } else {
            System.out.println("Oops! Even the Luminara need clear signals");
            execute(noun);
        }
    }
}
