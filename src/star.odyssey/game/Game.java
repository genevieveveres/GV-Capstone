package star.odyssey.game;

import star.odyssey.command.CommandManager;
import star.odyssey.sound.BackgroundAudioPlayer;
import star.odyssey.ui.DisplayUI;

public class Game {
    private final GameState gameState;
    private boolean isRunning;
    private final CommandManager commandManager;
    private final DisplayUI displayUI;
    private BackgroundAudioPlayer backgroundAudioPlayer = null;
    String settingsFilePath = "./data/userSettings.json";

    public Game(GameState gameState) {
        this.gameState = gameState;
        this.isRunning = false;
        this.commandManager = new CommandManager(this);
        this.displayUI = new DisplayUI(gameState);
    }

    public void start() {
        isRunning = true;
        mainGameLoop();
    }

    private void mainGameLoop() {
        while (isRunning) {
            String soundFilePath = getGameState().getPlayer().getLocation().getSoundFilePath();
            if (backgroundAudioPlayer != null) {
                backgroundAudioPlayer.stop();
            }
            backgroundAudioPlayer = new BackgroundAudioPlayer(soundFilePath);
            backgroundAudioPlayer.setVolume(GameUtil.jsonToInt(settingsFilePath, "current_volume"));
            backgroundAudioPlayer.loop();
            // Main loop for game execution; process commands and update game state
            displayUI.displayMainUI();
            String lastCommandResult = commandManager.getLastCommandResult();
            // Display the last command result
            System.out.println(lastCommandResult);

            // Process commands in a separate thread
            Thread commandThread = new Thread(commandManager::processCommands);

            // Start the command processing thread
            commandThread.start();

            try {
                // Wait for the command thread to finish before moving on
                commandThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if the game should continue after processing the command
            if (!isRunning) {
                // Display the goodbye message before breaking
                System.out.println(commandManager.getLastCommandResult());
                break;
            }
        }
    }

    public void stop() {
        isRunning = false;
    }

    public GameState getGameState() {
        return gameState;
    }

    // Additional methods as needed...
}
