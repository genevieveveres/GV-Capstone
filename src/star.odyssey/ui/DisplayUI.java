package star.odyssey.ui;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.location.Location;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class DisplayUI {
    private final Player player;

    public DisplayUI(GameState gameState) {
        this.player = gameState.getPlayer();
    }

    public void displayMainUI() {
        clearScreen();
        displayPlayerInfo();
        displayLocationInfo();
    }

    public void displayPlayerInfo() {
        // Display player info
        printDivider("Player Info");
        System.out.println(makeMagenta("Name: ") + player.getName() + ", " + makeMagenta("Health: ") + player.getHealth());
        // Display player inventory
        System.out.println(makeMagenta("Inventory:"));
        player.getInventory().forEach(item -> {
            System.out.println(item.getName());
        });
    }

    public void displayLocationInfo() {
        // Display player location
        printDivider("Location Info");
        Location currentLocation = player.getLocation();
        System.out.println(makeMagenta("Current Location: ") + currentLocation.getName());
        // Display location description
        System.out.println(wrapText(makeMagenta("Description: ")
                + currentLocation.getDescription()));
        // Display location connections
        System.out.println(makeMagenta("Connections:"));
        currentLocation.getConnections().entrySet().forEach(location -> {
            System.out.println(location.getKey() + " - " + location.getValue().getName());
        });
        // Display location items
        System.out.println(makeMagenta("Items:"));
        currentLocation.getItems().forEach(item -> {
            System.out.println(item.getName());
        });
        // Display location NPCs
        System.out.println(makeMagenta("NPCs:"));
        currentLocation.getNPCs().forEach(npc -> {
            System.out.println(npc.getName());
        });
        printDivider();
    }


}
