package star.odyssey.command;

public class CommandManager {
    private CommandReader reader;
    private CommandParser parser;
    private CommandIdentifier identifier;
    private CommandExecutor executor;
    private CommandConfig config;

    public CommandManager() {
        this.config = new CommandConfig("commands.json");
        // Initialize the command processing components
        reader = new CommandReader();
        parser = new CommandParser();
        identifier = new CommandIdentifier(config);
        executor = new CommandExecutor();
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
                System.out.println("Command not recognized.");
            }
        }
    }
}
