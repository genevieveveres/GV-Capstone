package star.odyssey.location;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameMap {
//    private List<Location> locations;
//    private Location currentLocation;
//
//    public GameMap(String jsonFilePath) {
//        initializeGameMap(jsonFilePath);
//    }
//
//    private void initializeGameMap(String jsonFilePath) {
//        try (FileReader reader = new FileReader(jsonFilePath)) {
//            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
//
//            JsonArray locationsArray = jsonObject.getAsJsonArray("locations");
//            if (locationsArray == null) {
//                locations = getDefaultLocations();
//                return;
//            }
//
//            locations = new ArrayList<>();
//
//            for (int i = 0; i < locationsArray.size(); i++) {
//                JsonObject locationObject = locationsArray.get(i).getAsJsonObject();
//
//                String index = locationObject.get("index").getAsString();
//                String name = locationObject.get("name").getAsString();
//                String description = locationObject.get("description").getAsString();
//                String detailedDescription = locationObject.get("detailed_description").getAsString();
//                JsonObject connectionsObject = locationObject.getAsJsonObject("connections");
//                JsonArray entities = locationObject.getAsJsonArray("entities");
//                JsonArray inventory = locationObject.getAsJsonArray("inventory");
//
//                Location location = new Location(index, name, description, detailedDescription, connectionsObject, entities, inventory);
//
//                if (connectionsObject != null) {
//                    for (Map.Entry<String, JsonElement> entry : connectionsObject.entrySet()) {
//                        String direction = entry.getKey();
//                        String connectedLocationIndex = entry.getValue().getAsString();
//                        Location connectedLocation = findLocationByIndex(connectedLocationIndex);
//                        location.addConnection(direction, connectedLocation);
//                    }
//                }
//
//                if (entities != null) {
//                    for (int j = 0; j < entities.size(); j++) {
//                        // Parse entity details and add to the location's entities list
//                    }
//                }
//
//                if (inventory != null) {
//                    for (int j = 0; j < inventory.size(); j++) {
//                        // Parse inventory item details and add to the location's inventory list
//                    }
//                }
//
//                locations.add(location);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Location getCurrentLocation() {
//        return currentLocation;
//    }
//
//    public void setCurrentLocation(Location location) {
//        currentLocation = location;
//    }
//
//
//    public Location findLocationByIndex(String index) {
//        for (Location location : locations) {
//            if (location.getIndex().equals(index)) {
//                return location;
//            }
//        }
//        return null; // Handle case when location is not found
//    }
//
//    private List<Location> getDefaultLocations() {
//        // Provide default locations if JSON file reading fails
//        return List.of();
//    }
//
//    public void displayMap() {
//        // Display the map to the player (e.g., current location, nearby locations)
//    }
//
//    public void updateLocation(Location location) {
//        // Update player's current location on the map
//    }
//
//    public void addLocation(Location location) {
//        // Add new locations to the game map
//        locations.add(location);
//    }
//
//    public List<Location> getLocations() {
//        return locations;
//    }
//
//    public static void main(String[] args) {
//        GameMap gameMap = new GameMap("data/locations.json");
//
//        // Display basic information about locations
//        for (Location location : gameMap.getLocations()) {
//            System.out.println("Location: " + location.getName());
//            System.out.println("Description: " + location.getDescription());
//            System.out.println("Connections: " + location.getConnections());
//            System.out.println("Entities: " + location.getEntities());
//            System.out.println("Inventory: " + location.getInventory());
//            System.out.println("--------------");
//        }
//    }
}
