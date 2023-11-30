package star.odyssey.character;

import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;

import java.util.List;
import java.util.Map;

public class Player extends Entity {

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "player_cmd_txt");

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
            return item.getName() + txtMap.get("item_unmovable");
        }
        if (item.isHidden()) {
            return item.getName() + txtMap.get("item_hidden");
        }
        if (!item.isActive()) {
            return item.getName() + txtMap.get("item_inactive");
        }

        this.inventory.add(item);
        this.location.removeItem(item);
        return item.getName() + txtMap.get("item_get");
    }

    public String dropItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                location.addInventory(item);
                return itemName + txtMap.get("item_drop");
            }
        }
        return txtMap.get("item_drop_fail") + itemName;
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
