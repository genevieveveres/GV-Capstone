package star.odyssey.game;

import star.odyssey.command.CommandManager;
import star.odyssey.sound.BackgroundAudioPlayer;
import star.odyssey.ui.DisplayUI;
import star.odyssey.ui.MainMenu;
import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class Game {

    // INSTANCE VARIABLES
    private final GameState gameState;
    private static boolean isRunning;
    private final CommandManager commandManager;
    private final DisplayUI displayUI;
    private static BackgroundAudioPlayer backgroundAudioPlayer = null;
    String settingsFilePath = "./data/userSettings.json";

    // CONSTRUCTORS
    public Game(GameState gameState) {
        this.gameState = gameState;
        isRunning = false;
        this.commandManager = new CommandManager(this);
        this.displayUI = new DisplayUI(gameState);
    }

    // METHODS
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

            if (!(gameState.getPlayer().isAlive())) {
                // Perform additional actions here if needed before ending the game
                Game.playerDefeated();
            }

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

    public static void stop() {
        backgroundAudioPlayer.stop();
        isRunning = false;
    }

    public static void playerDefeated() {
        pauseDisplay();
        Game.stop();
        clearScreen();
        MainMenu.execute();
    }

    // GETTERS AND SETTERS
    public GameState getGameState() {
        return gameState;
    }
}
