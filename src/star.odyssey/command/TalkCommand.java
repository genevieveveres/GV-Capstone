package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;

public class TalkCommand implements Command {
    private final GameState gameState;

    public TalkCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String npcName) {
        if (npcName == null || npcName.trim().isEmpty()) {
            return "Talking to the void? It's a good listener but not much of a talker. Try naming someone!";
        }

        Player player = gameState.getPlayer();
        NPC npc = gameState.getNpcManager().getNPC(npcName);

        if (npc != null && player.getLocation().getNPCs().contains(npc)) {
            return npc.talk();
        } else {
            return "Seems " + npcName + " is off on their own adventure. Or just really good at hide and seek.";
        }
    }
}
