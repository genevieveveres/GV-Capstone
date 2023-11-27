package star.odyssey.command;

import star.odyssey.character.Entity;
import star.odyssey.character.Player;
import star.odyssey.inventory.Item;
import star.odyssey.location.Location;

public class LookCommand implements Command {
    private final Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute(String noun) {
        Describable target = findTarget(noun);
        if (target != null) {
            System.out.println(target.getDetailedDescription());
        } else {
            System.out.println("You don't see anything special.");
        }
    }

    private Describable findTarget(String noun) {
        Location currentLocation = player.getLocation();

        // Check if the noun matches the current location's name
        if (currentLocation.getName().equalsIgnoreCase(noun)) {
            return currentLocation;
        }

        // Search for a matching character in the current location
        for (Entity entity : currentLocation.getEntities()) {
            if (entity.getName().equalsIgnoreCase(noun)) {
                return entity;
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
