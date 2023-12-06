package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.combat.CombatEngine;
import star.odyssey.game.GameState;

public class AttackCommand implements Command {
    private final GameState gameState;

    public AttackCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String targetName) {
        Player player = gameState.getPlayer();
        NPC target = gameState.getEntityManager().getNPC(targetName);

        if (target == null) {
            return "No such enemy found.";
        }

        CombatEngine combatEngine = new CombatEngine(player, target);

        return combatEngine.startCombat();
    }
}
