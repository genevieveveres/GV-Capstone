package star.odyssey.command;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;

import java.util.Map;

public class UseCommand implements Command {
    private final GameState gameState;
    String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "use_cmd");

    public UseCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String itemName) {

        if (itemName == null || itemName.trim().isEmpty()) {
            return txtMap.get("item_unknown");
        }

        Player player = gameState.getPlayer();
        Item playerItem = player.getInventoryItem(itemName);
        Item locationItem = player.getLocation().getItem(itemName);

        if (locationItem == null && playerItem == null) {
            return itemName + txtMap.get("item_null");
        } else if (locationItem == null) {
            return player.useItem(playerItem);
        } else {
            return player.useItem(locationItem);
        }
    }
}

