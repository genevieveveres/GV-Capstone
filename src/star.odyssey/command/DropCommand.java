package star.odyssey.command;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;

public class DropCommand implements Command {
    private final GameState gameState;

    public DropCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            return "You whisper 'drop' into the void, but it seems the void needs a little more info to comply.";
        }

        Player player = gameState.getPlayer();
        return player.dropItem(itemName);
    }
}
