package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.combat.CombatEngine;
import star.odyssey.game.Game;
import star.odyssey.game.GameState;

import java.util.List;

public class AttackCommand implements Command {
    private final GameState gameState;

    public AttackCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String targetName) {

        if (targetName == null || targetName.trim().isEmpty()) {
            return "No target specified.";
        }

        Player player = gameState.getPlayer();

        List<NPC> npcsInLocation = player.getLocation().getNpcs();

        NPC target = npcsInLocation.stream()
                .filter(npc -> npc.getName().equalsIgnoreCase(targetName))
                .findFirst()
                .orElse(null);

        if (target == null) {
            return "No such enemy found in your current location.";
        }

        CombatEngine combatEngine = new CombatEngine(player, target);

        return combatEngine.startCombat();
    }
}
