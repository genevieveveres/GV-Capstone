package star.odyssey.command;

import star.odyssey.game.Game;
import star.odyssey.game.GameUtil;

import java.util.Map;
import java.util.Scanner;

public class QuitCommand implements Command {
    private final Scanner scanner;
    private final Game game;
    String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "quit_cmd");
    private final Map<String, String> optionsMap = GameUtil.jsonToStringMap(gameTxtFilePath, "quit_options");

    public QuitCommand(Game game) {
        scanner = new Scanner(System.in);
        this.game = game;
    }

    public String optionsValues(Map<String, String> optionsMap) {
        StringBuilder optionsStr = new StringBuilder();
        for (Map.Entry<String, String> option : optionsMap.entrySet()) {
            if (!optionsStr.toString().isEmpty()) {
                optionsStr.append(", ");
            }
            optionsStr.append(option.getValue());
        }

        return optionsStr.toString();
    }

    @Override
    public String execute(String noun) {
        String optionChoices = GameUtil.optionsValues(optionsMap);
        String[] optionsList = optionChoices.split(", ");

        System.out.print(txtMap.get("quit_question") + optionsList[0] + " or " + optionsList[1] + ": ");
        String response = scanner.nextLine().trim().toLowerCase();
        String optionKey = GameUtil.getValueKey(optionsMap, response).toString();

        if ("[1]".equals(optionKey)) {
            game.stop();
            return txtMap.get("quit_confirm");
        } else if ("[2]".equals(optionKey)) {
            return txtMap.get("quit_deny");
        } else {
            System.out.println(txtMap.get("quit_fail"));
            return execute(noun);
        }
    }
}
