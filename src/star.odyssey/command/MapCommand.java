package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.map.GameMap;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class MapCommand implements Command {
    private final GameMap gameMap;
    private final GameState gameState;

    public MapCommand(GameState gameState) {
        this.gameMap = new GameMap();
        this.gameState = gameState;
    }

    @Override
    public String execute(String noun) {
        gameState.getLocationManager().setVisitedLocations(gameState.getPlayer().getLocation().getIndex());
        clearScreen();
        System.out.println();
        System.out.println(gameMap.drawGameMap(gameState.getLocationManager().getVisitedLocations()));
        pauseDisplay();
        return noun;
    }
}
