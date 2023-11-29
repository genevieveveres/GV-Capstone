package star.odyssey.command;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.inventory.Item;

public class GetCommand implements Command {
    private final GameState gameState;

    public GetCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String itemName) {

        if (itemName == null || itemName.trim().isEmpty()) {
            return "It seems like you're trying to get something, but what exactly?";
        }

        Player player = gameState.getPlayer();
        Item item = player.getLocation().getItem(itemName);

        if (item == null) {
            return "In an alternate universe, '" + itemName + "' might exist here. Sadly, not in this one.";
        }

        return player.getItem(item);
    }
}

