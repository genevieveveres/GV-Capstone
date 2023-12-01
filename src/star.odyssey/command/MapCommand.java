package star.odyssey.command;

import star.odyssey.map.GameMap;
import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class MapCommand implements Command {
    private final GameMap gameMap;

    public MapCommand() {
        this.gameMap = new GameMap();
    }

    @Override
    public String execute(String noun) {
        clearScreen();
        System.out.println();
        System.out.println(gameMap.drawGameMap());
        pauseDisplay();
        return noun;
    }
}
