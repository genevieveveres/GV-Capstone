package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;
import java.util.Map;

public class TalkCommand implements Command {

    // INSTANCE VARIABLES
    private final GameState gameState;
    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "talk_cmd");

    // CONSTRUCTORS
    public TalkCommand(GameState gameState) {
        this.gameState = gameState;
    }

    // METHODS
    @Override
    public String execute(String npcName) {
        if (npcName == null || npcName.trim().isEmpty()) {
            return txtMap.get("talk_null");
        }

        Player player = gameState.getPlayer();
        NPC npc = gameState.getEntityManager().getNPC(npcName);

        if (npc != null && player.getLocation().getNPCs().contains(npc)) {
            return npc.talk();
        } else {
            return npcName + txtMap.get("talk_unknown");
        }
    }
}
