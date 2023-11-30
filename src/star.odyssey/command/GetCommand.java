package star.odyssey.command;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;

import java.util.Map;

public class GetCommand implements Command {
    private final GameState gameState;
    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "get_cmd");

    public GetCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String itemName) {

        if (itemName == null || itemName.trim().isEmpty()) {
            return txtMap.get("item_unknown");
        }

        Player player = gameState.getPlayer();
        Item item = player.getLocation().getItem(itemName);

        if (item == null) {
            return itemName + txtMap.get("item_null");
        }

        return player.getItem(item);
    }
}

