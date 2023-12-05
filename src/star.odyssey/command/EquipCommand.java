package star.odyssey.command;

import star.odyssey.character.Entity;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.inventory.Weapon;

public class EquipCommand implements Command {
    private final GameState gameState;

    public EquipCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String weaponName) {
        Player player = gameState.getPlayer();
        Weapon weapon = (Weapon) gameState.getItemManager().getItem(weaponName);

        if (weapon == null) {
            return "Weapon not found.";
        }

        return player.equip(weapon);
    }
}
