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
        printDivider();
        System.out.println("Name: " + player.getName() + ", Health: " + player.getHealth());
        printDivider();
    }

    public void displayLocationInfo() {
        // Display player location
        Location currentLocation = player.getLocation();
        System.out.println("Current Location: " + currentLocation.getName());
        printDivider();
        System.out.println(wrapText(currentLocation.getDescription()));
        printDivider();
        System.out.println("Connections:");
        currentLocation.getConnections().entrySet().forEach(location -> {
            System.out.println(location.getKey() + " - " + location.getValue().getName());
        });
        printDivider();
        System.out.println("Items:");
        currentLocation.getItems().forEach(item -> {
            System.out.println(item.getName());
        });
        printDivider();
    }


}
