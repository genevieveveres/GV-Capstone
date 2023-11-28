package star.odyssey.command;

import star.odyssey.game.GameState;

public class CommandManager {
    private final CommandReader reader;
    private final CommandParser parser;
    private final CommandIdentifier identifier;
    private final CommandExecutor executor;
    private final CommandConfig config;

    public CommandManager(GameState gameState) {
        this.config = new CommandConfig("./data/commands.json");
        reader = new CommandReader();
        parser = new CommandParser();
        identifier = new CommandIdentifier(config);
        executor = new CommandExecutor(gameState);
    }

    public void processCommands() {
        while (true) {
            // Read command input from the user
            String input = reader.readCommand();

            // Parse the input to extract verb and noun
            ParsedCommand parsedCommand = parser.parseCommand(input);

            // Identify the command from the verb
            String command = identifier.identifyCommand(parsedCommand.getVerb());

            if (command != null) {
                // Execute the command if identified
                executor.executeCommand(command, parsedCommand.getNoun());
            } else {
                System.out.println("Uh oh, your command got tangled in the gravity-defying trees\nType 'help' for a navigation chart from the Luminara command center");
            }
        }
    }
}
