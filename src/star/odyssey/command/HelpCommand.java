package star.odyssey.command;

import star.odyssey.game.GameUtil;
import java.util.Map;

import static star.odyssey.ui.ConsoleDisplayUtils.wrapText;

// Command to display a list of available commands.
public class HelpCommand implements Command {

    // INSTANCE VARIABLES
    private final Map<String, Command> commandMap; // Reference to the map of commands.
    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "help_cmd");

    // CONSTRUCTORS
    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap; // Constructor initializes the command map.
    }

    // METHODS
    @Override
    public String execute(String noun) {
        StringBuilder result = new StringBuilder(txtMap.get("builderStart"));
        for (String commandKey : commandMap.keySet()) {
            result.append("- ").append(commandKey).append(" "); // Appending each available command.
        }

        return wrapText(result.toString().trim());
    }
}
