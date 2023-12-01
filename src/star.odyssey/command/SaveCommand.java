package star.odyssey.command;

import star.odyssey.game.GameSave;
import star.odyssey.game.GameState;

import java.io.IOException;

public class SaveCommand implements Command {

    private final GameState gameState;

    public SaveCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String noun) {
        try {
            GameSave.saveGame(gameState);
            return "Game saved successfully!";
        } catch (IOException e) {
            return "Error saving game: " + e.getMessage();
        }
    }
}
