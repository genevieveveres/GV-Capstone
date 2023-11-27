// Location.java
package star.odyssey.location;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import star.odyssey.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String index;
    private String name;
    private String description;
    private String detailedDescription;
    private Map<String, Location> connections;
    private List<Inventory> inventory;
    private List<Character> entities;

    public Location(String index, String name, String description, String detailedDescription, JsonObject connections, JsonArray entities, JsonArray inventory) {
        this.index = index;
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.connections = new HashMap<>();
        this.entities = new ArrayList<>();
        this.inventory = new ArrayList<>();

        parseConnections(connections);
        parseEntities(entities);
        parseInventory(inventory);
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public Map<String, Location> getConnections() {
        return connections;
    }

    public List<Inventory> getInventory() {
        return inventory;
    }

    public List<Character> getEntities() {
        return entities;
    }

    public void explore() {
        // Logic for exploring the location (e.g., find items, encounter characters)
    }

    public void addConnection(String direction, Location location) {
        this.connections.put(direction, location);
        // Add directional connections to other locations
    }

    public void addEntity(Character entity) {
        if (!this.entities.contains(entity)) {
            this.entities.add(entity);
        }
    }

    public void addInventory(Inventory inventory) {
        if (!this.inventory.contains(inventory)) {
            this.inventory.add(inventory);
        }
    }

    private void parseConnections(JsonObject connectionsObject) {
        if (connectionsObject != null) {
            for (Map.Entry<String, JsonElement> entry : connectionsObject.entrySet()) {
                String direction = entry.getKey();
                String connectedLocationIndex = entry.getValue().getAsString();
                Location connectedLocation = findLocationByIndex(connectedLocationIndex);
                addConnection(direction, connectedLocation);
            }
        }
    }

    private void parseEntities(JsonArray entitiesArray) {
        if (entitiesArray != null) {
            for (JsonElement element : entitiesArray) {

            }
        }
    }

    private void parseInventory(JsonArray inventoryArray) {
        if (inventoryArray != null) {
            for (JsonElement element : inventoryArray) {

            }
        }
    }

    private Location findLocationByIndex(String index) {
        // Implement logic to find a location by index
        return null;
    }

    // Other methods, if needed...
}
