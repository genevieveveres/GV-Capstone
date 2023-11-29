package star.odyssey.character;

import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;

import java.util.List;

public class Player extends Entity {

    public Player() {
        super();
    }

    public Player(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive) {
        super(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive);
    }

    public void move() {
        // Implement player-specific movement (e.g., based on player input)
    }

    public void attack() {
        // Player-specific attack implementation (e.g., combat mechanics)
    }

    public void defend() {
        // Player-specific defense implementation (e.g., block, dodge)
    }

    public String getItem(Item item) {
        if (!item.isMovable()) {
            return item.getName() + " is stubbornly rooted like a space rock. It's not going anywhere.";
        }
        if (item.isHidden()) {
            return item.getName() + " must be cloaked in invisibility! It's beyond your grasp.";
        }
        if (!item.isActive()) {
            return item.getName() + " appears dormant, like a hibernating alien. Maybe try again when it's awake?.";
        }

        this.inventory.add(item);
        this.location.removeItem(item);
        return item.getName() + " has been successfully teleported to your inventory";
    }

    public String dropItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                location.addInventory(item);
                return "You dropped " + itemName + ". It's now contemplating its life choices on the ground.";
            }
        }
        return "The art of letting go is profound, but it helps if you actually have " + itemName + " first.";
    }


    public void useItem(Item item) {
        // Implement usage of items from the inventory
    }

    public void interactWithNPC(NPC npc) {
        // Define player interaction with NPCs (e.g., start dialogue)
    }

    public void equipWeapon(Weapon weapon) {
        // Equip or change weapons for the player
    }

    // Additional methods if necessary...
}
