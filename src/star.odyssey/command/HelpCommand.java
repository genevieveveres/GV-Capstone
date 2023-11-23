package star.odyssey.command;

import java.util.Map;

// Command to display a list of available commands.
public class HelpCommand implements Command {
    private final Map<String, Command> commandMap; // Reference to the map of commands.

    public HelpCommand(Map<String, Command> commandMap) {
        this.commandMap = commandMap; // Constructor initializes the command map.
    }

    @Override
    public void execute(String noun) {
        System.out.println("Available Commands:");
        for (String commandKey : commandMap.keySet()) {
            System.out.println("- " + commandKey); // Iterating and displaying each available command.
        }
    }
}
