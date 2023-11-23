package star.odyssey.game;

import star.odyssey.character.Player;
import star.odyssey.command.CommandManager;
import star.odyssey.location.GameMap;

public class Game {
    private Player player;
    private GameMap gameMap;
    private boolean isRunning;
    private CommandManager commandManager;

    public Game() {
        initializeGameState();
    }

    private void initializeGameState() {
        // Initialize game components like player, game map, and command manager
        this.player = new Player();
        this.gameMap = new GameMap();
        this.commandManager = new CommandManager();
        this.isRunning = false;
    }

    public void start() {
        // Start the game loop, set `isRunning` to true
        isRunning = true;
        mainGameLoop();
    }

    private void mainGameLoop() {
        // Main loop for game execution; process commands and update game state
        while (isRunning) {
            commandManager.processCommands();
            // Add additional game loop logic here (e.g., checking game state, handling events)
        }
    }

    public void stop() {
        // Stop the game loop, perform cleanup if necessary
        isRunning = false;
    }

    // Additional methods if necessary...
}
