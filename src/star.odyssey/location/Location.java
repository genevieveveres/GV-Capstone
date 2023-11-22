package star.odyssey.location;

import star.odyssey.character.Character;
import star.odyssey.inventory.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private String detailedDescription;
    private Map<String, Location> connectedLocations;
    private List<Item> items;
    private List<Character> characters;
    private List<Location> internalLocations;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.connectedLocations = new HashMap<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.internalLocations = new ArrayList<>();
    }

    public void explore() {
        // Logic for exploring the location (e.g., find items, encounter characters)
    }

    public void addConnection(String direction, Location location) {
        // Add directional connections to other locations
    }

    public void addItem(Item item) {
        // Add items to the location for players to find
    }

    public void addCharacter(Character character) {
        // Place characters (NPCs or enemies) in the location
    }

    public void addInternalLocation(Location location) {
        // Add sub-locations within this location (e.g., rooms in a building)
    }

    // Additional methods if necessary...
}
