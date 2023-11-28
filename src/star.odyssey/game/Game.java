package star.odyssey.game;

import star.odyssey.command.CommandManager;

public class Game {
    private final GameState gameState;
    private boolean isRunning;
    private final CommandManager commandManager;

    public Game(GameState gameState) {
        this.gameState = gameState;
        this.isRunning = false;
        this.commandManager = new CommandManager(gameState);
        // Additional initialization as needed
    }

    public void start() {
        isRunning = true;
        mainGameLoop();
    }

    private void mainGameLoop() {
        while (isRunning) {
            // Main loop for game execution; process commands and update game state
            commandManager.processCommands();
            // Additional game loop logic here (e.g., updating game state, checking for game over conditions)
        }
    }

    public void stop() {
        isRunning = false;
        // Perform any necessary cleanup or finalization here
    }

    public GameState getGameState() {
        return gameState;
    }

    // Additional methods as needed...
}
