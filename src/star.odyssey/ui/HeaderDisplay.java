package star.odyssey.ui;

import star.odyssey.character.Player;
import star.odyssey.game.GameState;
import star.odyssey.location.Location;

public class HeaderDisplay {
    private final Player player;

    public HeaderDisplay(GameState gameState) {
        this.player = gameState.getPlayer();
    }

    public void displayHeader() {
        System.out.print("\033[H\033[2J");
        // Display player location
        Location currentLocation = player.getLocation();
        System.out.print("\033[42m");
        System.out.println("Current Location: " + currentLocation.getName());
        System.out.print("\033[0m");

    }
}
