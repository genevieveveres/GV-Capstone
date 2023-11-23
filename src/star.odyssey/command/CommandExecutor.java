package star.odyssey.command;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<String, Command> commandMap; // Stores command instances against their keys.

    public CommandExecutor() {
        commandMap = new HashMap<>();
        // Initialize other commands as needed
        commandMap.put("help", new HelpCommand(commandMap));
    }

    public void executeCommand(String commandKey, String noun) {
        Command command = commandMap.get(commandKey); // Retrieve the command object by key.
        if (command != null) {
            command.execute(noun); // Execute the command if found.
        } else {
            System.out.println("Command not recognized.");
        }
    }
}
