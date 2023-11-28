package star.odyssey.command;

import star.odyssey.game.Game;

import static star.odyssey.ui.ConsoleDisplayUtils.makeCyan;

public class CommandManager {
    private final CommandReader reader;
    private final CommandParser parser;
    private final CommandIdentifier identifier;
    private final CommandExecutor executor;
    private final CommandConfig config;
    private String lastCommandResult;

    public CommandManager(Game game) {
        this.config = new CommandConfig("./data/commands.json");
        reader = new CommandReader();
        parser = new CommandParser();
        identifier = new CommandIdentifier(config);
        executor = new CommandExecutor(game);
        lastCommandResult = "";
    }

    public void processCommands() {
        boolean isGameRunning = true;
        while (isGameRunning) {
            // Read command input from the user
            String input = reader.readCommand();

            // Parse the input to extract verb and noun
            ParsedCommand parsedCommand = parser.parseCommand(input);

            // Identify the command from the verb
            String command = identifier.identifyCommand(parsedCommand.getVerb());

            if (command != null) {
                isGameRunning = false;
                lastCommandResult = executor.executeCommand(command, parsedCommand.getNoun());
            } else {
                System.out.println("Uh oh, your command got tangled in the gravity-defying trees\nType 'help' for a navigation chart from the Luminara command center");
            }
        }
    }

    public String getLastCommandResult() {
        return makeCyan(lastCommandResult);
    }
}