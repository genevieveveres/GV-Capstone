package star.odyssey.combat;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;

public class CombatEngine {

    private final Player player;
    private final NPC npc;

    public CombatEngine(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public String startCombat() {
        StringBuilder combatLog = new StringBuilder();
        while (player.isAlive() && npc.isAlive()) {
            // Player's turn to attack
            combatLog.append(player.attack(npc)).append("\n");

            // Check if NPC is defeated
            if (!npc.isAlive()) {
                combatLog.append(npc.getName()).append(" has been defeated!\n");
                npc.dropItems(); // NPC drops items
                break;
            }

            // NPC's turn to attack
            combatLog.append(npc.attack(player)).append("\n");

            // Check if Player is defeated
            if (!player.isAlive()) {
                combatLog.append(player.getName()).append(" has been defeated!\n");
                break;
            }
        }
        return combatLog.toString();
    }
}
