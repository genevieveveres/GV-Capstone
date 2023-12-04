package star.odyssey.character;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class NPC extends Entity {
    private boolean hostile;
    private List<String> dialogueOptions;
    private String questDetails;
    private boolean hidden;

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "npc_cmd_txt");


    public NPC() {
        super();
    }

    public NPC(String index, String name, int health, int strength, int defense, String detailedDescription, Location location, List<Item> inventory, boolean isAlive, boolean hostile, List<String> dialogueOptions, String questDetails, boolean hidden) {
        super(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive);
        this.hostile = hostile;
        this.dialogueOptions = dialogueOptions;
        this.questDetails = questDetails;
        this.hidden = hidden;
    }

    public void move() {
        // NPC-specific movement behavior (e.g., patrol, follow player)
    }

    public void attack() {
        // Logic for NPC to attack, if hostile
    }

    public void defend() {
        // Defensive behavior for NPC during combat
    }

    public String talk() {
        if (dialogueOptions.isEmpty()) {
            return getName() + txtMap.get("ignored");
        }
        Random rand = new Random();
        return dialogueOptions.get(rand.nextInt(dialogueOptions.size()));
    }

    public void giveQuest() {
        // Logic for assigning quests to the player
    }

    public void dropLoot() {
        // Define loot dropping behavior upon NPC defeat
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
        jsonObject.addProperty("hostile", this.hostile);
        jsonObject.addProperty("hidden", this.hidden);

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

        // Updating basic attributes
        this.setHealth(jsonObject.get("health").getAsInt());
        this.setStrength(jsonObject.get("strength").getAsInt());
        this.setDefense(jsonObject.get("defense").getAsInt());
        this.setAlive(jsonObject.get("isAlive").getAsBoolean());
        this.setHostile(jsonObject.get("hostile").getAsBoolean());
        this.setHidden(jsonObject.get("hidden").getAsBoolean());

        // Updating the NPC's location
        String locationIndex = jsonObject.get("locationIndex").getAsString();
        Location location = locationManager.getLocation(locationIndex);
        this.setLocation(location);

        // Updating the NPC's inventory
        Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> itemIndices = gson.fromJson(jsonObject.get("inventoryIndices"), type);
        List<Item> updatedInventory = itemIndices.stream()
                .map(itemManager::getItem)
                .collect(Collectors.toList());
        this.setInventory(updatedInventory);
    }


    // Getters and setters
    public boolean isHostile() {
        return hostile;
    }

    public void setHostile(boolean hostile) {
        this.hostile = hostile;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    // Additional methods if necessary...
}
