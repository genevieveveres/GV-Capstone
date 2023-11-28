package star.odyssey.command;

import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.inventory.Item;
import star.odyssey.location.Location;

import static star.odyssey.ui.ConsoleDisplayUtils.wrapText;

public class LookCommand implements Command {
    private final Player player;

    public LookCommand(GameState gameState) {
        this.player = gameState.getPlayer();
    }

    @Override
    public String execute(String noun) {
        Describable target = findTarget(noun);
        if (target != null) {
            return wrapText(target.getDetailedDescription());
        } else {
            return "You don't see anything special.";
        }
    }

    private Describable findTarget(String noun) {
        Location currentLocation = player.getLocation();

        // Check if the noun matches the current location's name
        if (currentLocation.getName().equalsIgnoreCase(noun)) {
            return currentLocation;
        }

        // Search for a matching character in the current location
        for (NPC npc : currentLocation.getNPCs()) {
            if (npc.getName().equalsIgnoreCase(noun)) {
                return npc;
            }
        }

        // Search for a matching item in the current location
        for (Item item : currentLocation.getInventory()) {
            if (item.getName().equalsIgnoreCase(noun)) {
                return item;
            }
        }

        // If nothing matches, return null
        return null;
    }

    // Rest of the class...
}
