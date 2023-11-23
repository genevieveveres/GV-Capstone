package star.odyssey.command;

// Interface for all command types in the game.
public interface Command {
    void execute(String noun); // Execute the command with an optional noun argument.
}
