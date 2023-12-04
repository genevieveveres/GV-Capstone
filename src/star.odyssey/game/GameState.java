package star.odyssey.game;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import star.odyssey.character.EntityManager;
import star.odyssey.character.NPC;
import star.odyssey.character.Player;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

public class GameState implements SerializableRPGObject {
    private final Player player;
    private final EntityManager entityManager;
    private final ItemManager itemManager;
    private final LocationManager locationManager;

    public GameState(Player player, EntityManager entityManager, ItemManager itemManager, LocationManager locationManager) {
        this.player = player;
        this.entityManager = entityManager;
        this.itemManager = itemManager;
        this.locationManager = locationManager;
    }

    // Getters and setters

    public Player getPlayer() {
        return player;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    // Serialize and Deserialize
    @Override
    public String serialize() {
        Gson gson = new Gson();
        JsonObject gameStateJson = new JsonObject();

        // Serialize Player
        gameStateJson.addProperty("player", player.serialize());

        // Serialize NPCs
        JsonArray npcsJson = new JsonArray();
        for (NPC npc : entityManager.getAllNPCs().values()) {
            npcsJson.add(gson.fromJson(npc.serialize(), JsonObject.class));
        }
        gameStateJson.add("npcs", npcsJson);

        // Serialize Locations
        JsonArray locationsJson = new JsonArray();
        for (Location location : locationManager.getLocations().values()) {
            JsonObject locationJson = gson.fromJson(location.serialize(), JsonObject.class);
            locationJson.addProperty("visited", locationManager.getVisitedLocations().contains(location.getIndex()));
            locationsJson.add(locationJson);
        }
        gameStateJson.add("locations", locationsJson);

        // Serialize Items
        JsonArray itemsJson = new JsonArray();
        for (Item item : itemManager.getAllItems().values()) {
            itemsJson.add(gson.fromJson(item.serialize(), JsonObject.class));
        }
        gameStateJson.add("items", itemsJson);

        // Return the complete game state as a JSON string
        return gson.toJson(gameStateJson);
    }

    @Override
    public void deserialize(String serializedData, ItemManager itemManager, LocationManager locationManager, EntityManager entityManager) {

        JsonObject gameStateJson = new JsonObject();

        // Methods


    }


    // Additional methods to update and manage other parts of the game state
}
