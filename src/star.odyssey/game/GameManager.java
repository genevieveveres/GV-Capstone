package star.odyssey.game;

import star.odyssey.character.EntityManager;
import star.odyssey.character.Player;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.util.List;

public class GameManager {
    private final Game game;
    private final GameState gameState;
    private final ItemManager itemManager;
    private final EntityManager entityManager;
    private final LocationManager locationManager;

    public GameManager() {
        // Initialize managers independently
        itemManager = new ItemManager("./data/items.json");
        entityManager = new EntityManager("./data/entities.json");
        locationManager = new LocationManager("./data/locations.json");

        Player player = entityManager.getPlayer();
        validatePlayer(player);

        // Associate entities
        associateEntities(player);

        // Create the GameState
        gameState = new GameState(player, entityManager, itemManager, locationManager);

        // Create the game instance
        game = new Game(gameState);
    }

    private void associateEntities(Player player) {
        associatePlayerWithLocation(player);
        associatePlayerWithItems(player);
        associateNPCsWithLocations();
        associateItemsWithNPCs();
        associateItemsWithLocations();
    }

    private void validatePlayer(Player player) {
        if (player == null) {
            throw new IllegalStateException("Player not found in game data");
        }
    }

    private void associatePlayerWithLocation(Player player) {
        String locationIndex = entityManager.getPlayerLocationIndex();
        Location startingLocation = locationManager.getLocation(locationIndex);
        if (startingLocation != null) {
            player.setLocation(startingLocation);
        } else {
            throw new IllegalStateException("Starting location not found in game data");
        }
    }

    private void associatePlayerWithItems(Player player) {
        List<String> itemIndexes = entityManager.getPlayerItemIndexes();
        for (String itemIndex : itemIndexes) {
            Item item = itemManager.getItem(itemIndex);
            if (item != null) {
                player.getInventory().add(item);
            }
        }
    }

    private void associateNPCsWithLocations() {
        entityManager.getAllNPCs().values().forEach(npc -> {
            String locationIndex = entityManager.getNPCsLocationIndex(npc.getIndex());
            if (locationIndex != null) {
                Location location = locationManager.getLocation(locationIndex);
                if (location != null) {
                    location.addNPC(npc);
                }
            }
        });
    }

    private void associateItemsWithNPCs() {
        entityManager.getAllNPCs().values().forEach(npc -> {
            List<String> itemIndexes = entityManager.getNPCsItemIndexes(npc.getIndex());
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
