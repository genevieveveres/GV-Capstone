package star.odyssey.character;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.inventory.Weapon;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void equipWeapon(Weapon weapon) {
        // Equip or change weapons for the player
    }

    // Serialize and Deserialize
    @Override
    public String serialize() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("index", this.index);
        jsonObject.addProperty("health", this.health);
        jsonObject.addProperty("strength", this.strength);
        jsonObject.addProperty("defense", this.defense);
        jsonObject.addProperty("isAlive", this.isAlive);
        jsonObject.addProperty("locationIndex", this.location.getIndex());
        // Serialize inventory as a list of item indices
        List<String> inventoryIndices = this.inventory.stream()
                .map(Item::getIndex)
                .collect(Collectors.toList());
        jsonObject.add("inventoryIndices", gson.toJsonTree(inventoryIndices));

        return jsonObject.toString();
    }

    @Override
    public void deserialize(String serializedData, ItemManager itemManager, LocationManager locationManager, EntityManager entityManager) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(serializedData, JsonObject.class);

        this.setIndex(jsonObject.get("index").getAsString());
        this.setHealth(jsonObject.get("health").getAsInt());
        this.setStrength(jsonObject.get("strength").getAsInt());
        this.setDefense(jsonObject.get("defense").getAsInt());
        this.setAlive(jsonObject.get("isAlive").getAsBoolean());

        String locationIndex = jsonObject.get("locationIndex").getAsString();
        Location location = locationManager.getLocation(locationIndex);
        this.setLocation(location);

        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> itemIndices = gson.fromJson(jsonObject.get("inventoryIndices"), type);
        List<Item> inventory = itemIndices.stream()
                .map(itemManager::getItem)
                .collect(Collectors.toList());
        this.setInventory(inventory);
    }

    // Additional methods if necessary...
}
