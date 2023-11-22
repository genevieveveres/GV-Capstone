package star.odyssey.command;

public class CommandIdentifier {
    private CommandConfig config;

    public CommandIdentifier(CommandConfig config) {
        this.config = config;
        // Initialize with a CommandConfig to access command synonyms
    }

    public String identifyCommand(String commandWord) {
        if (commandWord == null) return null;
        // Iterate through command keys to find a matching command or synonym
        for (String key : config.getCommands().keySet()) {
            for (String synonym : config.getSynonyms(key)) {
                if (synonym.equalsIgnoreCase(commandWord)) {
                    // Return the main command word if a synonym matches
                    return key;
                }
            }
        }
        // Return null if no matching command is found
        return null;
    }
}
