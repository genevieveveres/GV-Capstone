package star.odyssey.command;

import star.odyssey.game.Game;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commandMap; // Stores command instances against their keys.

    public CommandExecutor(Game game) {
        commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand(commandMap));
        commandMap.put("quit", new QuitCommand(game));
        commandMap.put("look", new LookCommand(game.getGameState()));
        commandMap.put("move", new MoveCommand(game.getGameState()));
        commandMap.put("get", new GetCommand(game.getGameState()));
        // Initialize other commands as needed
    }

    public String executeCommand(String commandKey, String noun) {
        Command command = commandMap.get(commandKey); // Retrieve the command object by key.
        if (command != null) {
            return command.execute(noun); // Execute the command if found.
        } else {
            return "Looks like that command hit a black hole\nTry 'help' to find your way back on the starry path";
        }
    }
}
