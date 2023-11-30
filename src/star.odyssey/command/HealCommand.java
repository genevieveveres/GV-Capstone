package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;

import java.util.Map;

public class HealCommand implements Command {
    private final GameState gameState;
    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "heal_cmd");

    public HealCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String entityName) {
        Player player = gameState.getPlayer();
        if (entityName.equalsIgnoreCase(player.getName())) {
            player.heal();
            return txtMap.get("heal_yourself");
        }

        NPC npc = gameState.getEntityManager().getNPC(entityName);
        if (npc != null && player.getLocation().getNPCs().contains(npc)) {
            npc.heal();
            return txtMap.get("heal_npc_beginning") + npc.getName() + txtMap.get("heal_npc_end");
        } else {
            return txtMap.get("heal_null_beginning") + entityName + txtMap.get("heal_null_end");
        }
    }
}
