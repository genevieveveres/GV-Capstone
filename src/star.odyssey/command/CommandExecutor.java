package star.odyssey.command;

import star.odyssey.game.GameState;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commandMap; // Stores command instances against their keys.

    public CommandExecutor(GameState gameState) {
        commandMap = new HashMap<>();
        commandMap.put("help", new HelpCommand(commandMap));
        commandMap.put("quit", new QuitCommand());
        commandMap.put("look", new LookCommand(gameState));
        // Initialize other commands as needed
    }

    public void executeCommand(String commandKey, String noun) {
        Command command = commandMap.get(commandKey); // Retrieve the command object by key.
        if (command != null) {
            command.execute(noun); // Execute the command if found.
        } else {
            System.out.println("Looks like that command hit a black hole\nTry 'help' to find your way back on the starry path");
        }
    }
}
