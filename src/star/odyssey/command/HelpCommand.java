package star.odyssey.command;

import star.odyssey.game.GameUtil;
import star.odyssey.ui.ConsoleDisplayUtils;
import star.odyssey.ui.DisplayGameInfo;
import star.odyssey.ui.swing.HelpFrame;

import java.util.Map;

import static star.odyssey.ui.ConsoleDisplayUtils.*;
import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;

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
        //OLD manner of doing things
//        StringBuilder result = new StringBuilder(txtMap.get("builderStart"));
//        for (String commandKey : commandMap.keySet()) {
//            result.append("- ").append(commandKey).append(" "); // Appending each available command.
//        }
//
//        return wrapText(result.toString().trim());

        clearScreen();
        String helpText = GameUtil.jsonToString(gameTxtFilePath, "helpText");
        System.out.println(helpText);
        pauseDisplay();
        clearScreen();

        return "";
    }

}
