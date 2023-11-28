package star.odyssey.command;

import star.odyssey.game.Game;

import java.util.Scanner;

public class QuitCommand implements Command {
    private final Scanner scanner;
    private final Game game;

    public QuitCommand(Game game) {
        scanner = new Scanner(System.in);
        this.game = game;
    }

    @Override
    public String execute(String noun) {
        System.out.print("Thinking of warping back to reality? (yes/no)\n>");
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            game.stop();
            return "Rocketing back to normalcy! Remember, Earth has no glowing trees or melody mountains";
        } else if ("no".equals(response)) {
            return "That’s the spirit! Who needs Earth when you’ve got an alien world to explore?";
        } else {
            System.out.println("Oops! Even the Luminara need clear signals");
            return execute(noun);
        }
    }
}
