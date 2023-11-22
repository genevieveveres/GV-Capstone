package star.odyssey.command;

public class CommandManager {
    private final CommandReader reader;
    private final CommandParser parser;
    private final CommandIdentifier identifier;
    private final CommandExecutor executor;
    private final CommandConfig config;

    public CommandManager() {
        this.config = new CommandConfig("./data/commands.json");
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

    public static void main(String[] args) {
        // This is temporary and just for testing the command prompt
        CommandManager commandManager = new CommandManager();
        commandManager.processCommands();
    }
}
