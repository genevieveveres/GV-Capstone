package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;

public class HealCommand implements Command {
    private final GameState gameState;

    public HealCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String entityName) {
        Player player = gameState.getPlayer();
        if (entityName.equalsIgnoreCase(player.getName())) {
            player.heal();
            return "You whip out your emergency healing kit. Good as new!";
        }

        NPC npc = gameState.getEntityManager().getNPC(entityName);
        if (npc != null && player.getLocation().getNPCs().contains(npc)) {
            npc.heal();
            return "As the healing energy flows into " + npc.getName() + ", they chuckle, 'I haven't felt this good since I discovered space-coffee!'";
        } else {
            return "Your attempt to remote-heal " + entityName + " fails. If only your powers extended beyond this location.";
        }
    }
}
