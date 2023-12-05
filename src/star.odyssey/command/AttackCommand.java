package star.odyssey.command;

import star.odyssey.character.Entity;
import star.odyssey.character.NPC;
import star.odyssey.game.GameState;

public class AttackCommand implements Command {
    private final GameState gameState;

    public AttackCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String targetName) {
        Entity player = gameState.getPlayer();
        NPC target = gameState.getEntityManager().getNPC(targetName);

        if (target == null) {
            return "No such enemy found.";
        }

        return player.attack(target);
    }
}
