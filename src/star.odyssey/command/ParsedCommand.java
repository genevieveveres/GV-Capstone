package star.odyssey.command;

public class ParsedCommand {
    private final String verb;
    private final String noun;

    public ParsedCommand(String verb, String noun) {
        // Initialize with the verb and noun extracted from the input
        this.verb = verb;
        this.noun = noun;
    }

    public String getVerb() {
        // Return the verb part of the command
        return verb;
    }

    public String getNoun() {
        // Return the noun part of the command, if any
        return noun;
    }
}
