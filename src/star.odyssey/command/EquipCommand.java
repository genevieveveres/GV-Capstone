package star.odyssey.command;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;

public class EquipCommand implements Command {
    private final GameState gameState;

    public EquipCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String weaponName) {
        Player player = gameState.getPlayer();

        for (Item item : player.getInventory()) {
            if (item.getName().equalsIgnoreCase(weaponName) && item instanceof Weapon) {
                return player.equip((Weapon) item);
            }
        }

        return "Weapon not found in inventory.";
    }
}
