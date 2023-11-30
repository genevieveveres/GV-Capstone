package star.odyssey.command;

import star.odyssey.game.Game;
import star.odyssey.game.GameUtil;

import java.util.Map;
import java.util.Scanner;

public class QuitCommand implements Command {
    private final Scanner scanner;
    private final Game game;
    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "quit_cmd");

    public QuitCommand(Game game) {
        scanner = new Scanner(System.in);
        this.game = game;
    }

    @Override
    public String execute(String noun) {
        System.out.print(txtMap.get("quit_question"));
        String response = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(response)) {
            game.stop();
            return txtMap.get("quit_confirm");
        } else if ("no".equals(response)) {
            return txtMap.get("quit_deny");
        } else {
            System.out.println(txtMap.get("quit_fail"));
            return execute(noun);
        }
    }
}
