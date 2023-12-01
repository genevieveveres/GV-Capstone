package star.odyssey.command;

import star.odyssey.game.GameUtil;

import java.util.Map;

// Command to display a list of available commands.
public class HelpCommand implements Command {
    private final Map<String, Command> commandMap; // Reference to the map of commands.

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "help_cmd");

    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap; // Constructor initializes the command map.
    }

    @Override
    public String execute(String noun) {
        StringBuilder result = new StringBuilder(txtMap.get("builderStart"));
        for (String commandKey : commandMap.keySet()) {
            result.append("- ").append(commandKey).append("\n"); // Appending each available command.
        }

        return result.toString().trim();
    }
}
