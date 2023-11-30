package star.odyssey.game;

import star.odyssey.character.EntityManager;
import star.odyssey.character.Player;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.LocationManager;

public class GameState {
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


    // Additional methods to update and manage other parts of the game state
}
