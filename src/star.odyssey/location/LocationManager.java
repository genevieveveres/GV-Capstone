package star.odyssey.location;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import star.odyssey.character.NPC;
import star.odyssey.character.NPCManager;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationManager {
    private final Map<String, Location> locations;
    private final ItemManager itemManager;
    private final NPCManager npcManager;

    public LocationManager(String jsonFilePath, ItemManager itemManager, NPCManager npcManager) {
        this.itemManager = itemManager;
        this.npcManager = npcManager;
        locations = new HashMap<>();
        loadLocationsFromJson(jsonFilePath);
    }

    private void loadLocationsFromJson(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray locationsArray = jsonObject.getAsJsonArray("locations");

            for (JsonElement locationElement : locationsArray) {
                Location location = createLocation(locationElement.getAsJsonObject());
                locations.put(location.getIndex(), location);
            }

            for (JsonElement locationElement : locationsArray) {
                establishConnections(locationElement.getAsJsonObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Location createLocation(JsonObject locationObject) {
        String index = locationObject.get("index").getAsString();
        String name = locationObject.get("name").getAsString();
        String description = locationObject.get("description").getAsString();
        String detailedDescription = locationObject.get("detailed_description").getAsString();

        List<NPC> npcsList = parseNPCs(locationObject.getAsJsonArray("entities"));
        List<Item> inventoryList = parseInventory(locationObject.getAsJsonArray("inventory"));

        return new Location(index, name, description, detailedDescription, npcsList, inventoryList);
    }

    private List<NPC> parseNPCs(JsonArray npcsArray) {
        List<NPC> npcsList = new ArrayList<>();
        for (JsonElement element : npcsArray) {
            String npcIndex = element.getAsString();
            NPC npc = npcManager.getNPC(npcIndex);
            if (npc != null) {
                npcsList.add(npc);
            }
        }
        return npcsList;
    }

    private List<Item> parseInventory(JsonArray inventoryArray) {
        List<Item> inventoryList = new ArrayList<>();
        for (JsonElement element : inventoryArray) {
            String itemIndex = element.getAsString();
            Item item = itemManager.getItem(itemIndex);
            if (item != null) {
                inventoryList.add(item);
            }
        }
        return inventoryList;
    }

    private void establishConnections(JsonObject locationObject) {
        String index = locationObject.get("index").getAsString();
        Location location = locations.get(index);
        JsonObject connectionsObject = locationObject.getAsJsonObject("connections");

        for (Map.Entry<String, JsonElement> entry : connectionsObject.entrySet()) {
            String direction = entry.getKey();
            String connectedLocationIndex = entry.getValue().getAsString();
            Location connectedLocation = locations.get(connectedLocationIndex);
            location.addConnection(direction, connectedLocation);
        }
    }

    public Location getLocation(String index) {
        return locations.get(index);
    }

    // Additional methods as needed...
}
