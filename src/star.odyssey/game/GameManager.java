package star.odyssey.game;

import star.odyssey.character.EntityManager;
import star.odyssey.character.Player;
import star.odyssey.inventory.Item;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.Location;
import star.odyssey.location.LocationManager;

import java.util.List;
import java.util.Map;

public class GameManager {
    private Game game;
    private GameState gameState;
    private final ItemManager itemManager;
    private final EntityManager entityManager;
    private final LocationManager locationManager;
    private final String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "game_mgr");

    public GameManager() {
        this.itemManager = new ItemManager("./data/items.json");
        this.entityManager = new EntityManager("./data/entities.json");
        this.locationManager = new LocationManager("./data/locations.json");

        initializeNewGame();
    }

    private void initializeNewGame() {
        Player player = entityManager.getPlayer();
        validatePlayer(player);
        associateEntities(player);
        gameState = new GameState(player, entityManager, itemManager, locationManager);
        game = new Game(gameState);
    }

    public void loadSavedGame() {
        LoadGame loadGame = new LoadGame(gameState);
        loadGame.load();
        game = new Game(gameState);
        startGame();
    }

    public void startGame() {
        game.start();
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
            throw new IllegalStateException(txtMap.get("player_null"));
        }
    }

    private void associatePlayerWithLocation(Player player) {
        String locationIndex = entityManager.getPlayerLocationIndex();
        Location startingLocation = locationManager.getLocation(locationIndex);
        if (startingLocation != null) {
            player.setLocation(startingLocation);
            player.getLocation().setVisited(true);
        } else {
            throw new IllegalStateException(txtMap.get("location_null"));
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
                    npc.setLocation(location);
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

    // Additional methods as needed...
}
