package star.odyssey.game;

import star.odyssey.command.CommandManager;
import star.odyssey.ui.HeaderDisplay;

import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private final GameState gameState;
    private boolean isRunning;
    private final CommandManager commandManager;
    private HeaderDisplay headerDisplay;

    public Game(GameState gameState) {
        this.gameState = gameState;
        this.isRunning = false;
        this.commandManager = new CommandManager(gameState);
        this.headerDisplay = new HeaderDisplay(gameState);
        // Additional initialization as needed
    }

    public void start() {
        isRunning = true;
        mainGameLoop();
    }

    private void mainGameLoop() {
        while (isRunning) {
            // Main loop for game execution; process commands and update game state
            headerDisplay.displayHeader();
            String lastCommandResult = commandManager.getLastCommandResult();
            // display lastCommandResult
            System.out.println(lastCommandResult);

            // Process commands in a separate thread
            Thread commandThread = new Thread(() -> {
                commandManager.processCommands();
            });

            // Start the command processing thread
            commandThread.start();

            try {
                // Wait for the command thread to finish before moving on
                commandThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
