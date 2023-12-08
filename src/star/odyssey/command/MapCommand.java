package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.location.Location;
import star.odyssey.map.GameMap;

import java.util.List;
import java.util.stream.Collectors;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class MapCommand implements Command {
    private static GameState gameState;

    public MapCommand(GameState gameState) {
        this.gameState = gameState;
    }

    public String execute(String noun) {
        clearScreen();
        System.out.println();
        List<String> visitedLocations = gameState.getLocationManager().getLocations().values().stream()
                .filter(Location::isVisited)
                .map(Location::getIndex)
                .collect(Collectors.toList());
        System.out.println(((GameMap.drawGameMap(visitedLocations, gameState.getPlayer().getLocation().getIndex()))));
        pauseDisplay();
        return noun;
    }

}
