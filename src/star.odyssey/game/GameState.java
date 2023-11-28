package star.odyssey.game;

import star.odyssey.character.NPCManager;
import star.odyssey.character.Player;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.LocationManager;

public class GameState {
    private final Player player;
    private final NPCManager npcManager;
    private final ItemManager itemManager;
    private final LocationManager locationManager;

    public GameState(Player player, NPCManager npcManager, ItemManager itemManager, LocationManager locationManager) {
        this.player = player;
        this.npcManager = npcManager;
        this.itemManager = itemManager;
        this.locationManager = locationManager;
    }

    // Getters and setters

    public Player getPlayer() {
        return player;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }


    // Additional methods to update and manage other parts of the game state
}
