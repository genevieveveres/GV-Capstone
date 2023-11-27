package star.odyssey.game;

import star.odyssey.character.Player;
import star.odyssey.command.CommandManager;
import star.odyssey.location.GameMap;
import star.odyssey.location.Location;

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
        this.gameMap = new GameMap("data/locations.json");
        this.commandManager = new CommandManager(this.player);
        this.isRunning = false;

        // Set the starting location to "ship_entry"
        Location startingLocation = gameMap.findLocationByIndex("ship_entry");
        if (startingLocation != null) {
            gameMap.setCurrentLocation(startingLocation);
        }
    }

    public void start() {
        // Start the game loop, set `isRunning` to true
        isRunning = true;
        mainGameLoop();
    }

    private void mainGameLoop() {
        // Main loop for game execution; process commands and update game state
        while (isRunning) {
            // Display current location description at the beginning of each iteration
            displayCurrentLocationDetails();

            commandManager.processCommands();
            // Add additional game loop logic here (e.g., checking game state, handling events)
        }
    }

    private void displayCurrentLocationDetails() {
        Location currentLocation = gameMap.getCurrentLocation();
        if (currentLocation != null) {
            System.out.println("-------------------------------------------------------");
            System.out.println();
            System.out.println("Location: " + currentLocation.getName());
            System.out.println();
            System.out.println("Description: " + currentLocation.getDescription());
            System.out.println();
            System.out.println("-------------------------------------------------------");
        }
    }

    public void stop() {
        // Stop the game loop, perform cleanup if necessary
        isRunning = false;
    }

    // Additional methods if necessary...
}
