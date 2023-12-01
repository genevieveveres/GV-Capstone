package star.odyssey.location;

import star.odyssey.character.NPC;
import star.odyssey.command.Describable;
import star.odyssey.inventory.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location implements Describable {
    private String index;
    private String name;
    private String description;
    private String detailedDescription;
    private Map<String, Location> connections;
    private List<NPC> npcs;
    private List<Item> items;
    private String soundFilePath;

    public Location(String index, String name, String description, String detailedDescription, List<NPC> npcs, List<Item> items, String soundFilePath) {
        this.index = index;
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.connections = new HashMap<>();
        this.npcs = npcs;
        this.items = items;
        this.soundFilePath = soundFilePath;
    }

    // Getters and Setters
    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public Map<String, Location> getConnections() {
        return connections;
    }

    public List<NPC> getNPCs() {
        return npcs;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    // Action methods
    public void addConnection(String direction, Location location) {
        this.connections.put(direction, location);
    }

    public void addNPC(NPC npc) {
        if (!this.npcs.contains(npc)) {
            this.npcs.add(npc);
        }
    }

    public void addInventory(Item item) {
        if (!this.items.contains(item)) {
            this.items.add(item);
        }
    }

    public Item getItem(String itemName) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    // Additional methods if necessary...
}
