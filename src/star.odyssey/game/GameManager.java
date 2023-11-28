package star.odyssey.game;

import star.odyssey.character.NPCManager;
import star.odyssey.character.Player;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final Game game;
    private final GameState gameState;
    private ItemManager itemManager;
    private NPCManager npcManager;
    private final LocationManager locationManager;

    public GameManager() {
        // Initialize managers independently
        // itemManager = new ItemManager("./data/items.json");
        // npcManager = new NPCManager("./data/npcs.json");
        locationManager = new LocationManager("./data/locations.json");

        // Load and retrieve the starting location
        String startingLocationId = "ship_storeroom";
        Location startingLocation = locationManager.getLocation(startingLocationId);

        // Ensure the starting location is not null
        if (startingLocation == null) {
            throw new IllegalStateException("Starting location not found in game data");
        }

        // Create a Player instance with the starting location
        Player player = new Player("playerIndex", "PlayerName", 100, 10, 5, "A brave explorer", startingLocation, new ArrayList<>(), true);

        // Create the GameState
        gameState = new GameState(player, npcManager, itemManager, locationManager);

        // Create the game instance
        game = new Game(gameState);

        // Associate entities
        // associateEntities();
    }

    private void associateEntities() {
        associateNPCsWithLocations();
        associateItemsWithNPCs();
        associateItemsWithLocations();
    }

    private void associateNPCsWithLocations() {
        npcManager.getAllNPCs().values().forEach(npc -> {
            String locationIndex = npcManager.getNPCsLocationIndex(npc.getIndex());
            if (locationIndex != null) {
                Location location = locationManager.getLocation(locationIndex);
                if (location != null) {
                    location.addNPC(npc);
                }
            }
        });
    }

    private void associateItemsWithNPCs() {
        npcManager.getAllNPCs().values().forEach(npc -> {
            List<String> itemIndexes = npcManager.getNPCsItemIndexes(npc.getIndex());
            itemIndexes.forEach(itemIndex -> {
                Item item = itemManager.getItem(itemIndex);
                if (item != null) {
                    npc.getInventory().add(item);
                }
            });
        });
    }

    private void associateItemsWithLocations() {
        locationManager.getLocations().values().forEach(location -> {
            List<String> itemIndexes = locationManager.getLocationItemIndexes(location.getIndex());
            itemIndexes.forEach(itemIndex -> {
                Item item = itemManager.getItem(itemIndex);
                if (item != null) {
                    location.addInventory(item);
                }
            });
        });
    }

    public void startGame() {
        game.start();
    }

    // Additional methods as needed...
}
