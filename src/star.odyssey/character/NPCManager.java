package star.odyssey.character;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;
import star.odyssey.inventory.Item;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCManager {
    private Map<String, NPC> npcs;
    private ItemManager itemManager;
    private LocationManager locationManager;

    public NPCManager(String jsonFilePath, ItemManager itemManager, LocationManager locationManager) {
        this.itemManager = itemManager;
        this.locationManager = locationManager;
        npcs = new HashMap<>();
        loadNPCsFromJson(jsonFilePath);
    }

    private void loadNPCsFromJson(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray npcsArray = jsonObject.getAsJsonArray("npcs");

            for (JsonElement npcElement : npcsArray) {
                NPC npc = createNPC(npcElement.getAsJsonObject());
                npcs.put(npc.getIndex(), npc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions (file not found, JSON parsing errors, etc.)
        }
    }

    private NPC createNPC(JsonObject npcObject) {
        String index = npcObject.get("index").getAsString();
        String name = npcObject.get("name").getAsString();
        int health = npcObject.get("health").getAsInt();
        int strength = npcObject.get("strength").getAsInt();
        int defense = npcObject.get("defense").getAsInt();
        String detailedDescription = npcObject.get("detailed_description").getAsString();
        String locationIndex = npcObject.get("location").getAsString();
        Location location = locationManager.getLocation(locationIndex);
        List<Item> inventory = parseInventory(npcObject.getAsJsonArray("inventory"));
        boolean isAlive = npcObject.get("isAlive").getAsBoolean();
        boolean hostile = npcObject.get("hostile").getAsBoolean();
        List<String> dialogueOptions = parseDialogueOptions(npcObject.getAsJsonArray("dialogueOptions"));
        String questDetails = npcObject.get("questDetails").getAsString();

        return new NPC(index, name, health, strength, defense, detailedDescription, location, inventory, isAlive, hostile, dialogueOptions, questDetails);
    }

    private List<Item> parseInventory(JsonArray inventoryArray) {
        List<Item> inventory = new ArrayList<>();
        for (JsonElement element : inventoryArray) {
            String itemIndex = element.getAsString();
            Item item = itemManager.getItem(itemIndex);
            if (item != null) {
                inventory.add(item);
            }
        }
        return inventory;
    }

    private List<String> parseDialogueOptions(JsonArray dialogueOptionsArray) {
        List<String> dialogueOptions = new ArrayList<>();
        for (JsonElement element : dialogueOptionsArray) {
            dialogueOptions.add(element.getAsString());
        }
        return dialogueOptions;
    }

    public NPC getNPC(String index) {
        return npcs.get(index);
    }

    // Additional methods as needed...
}
