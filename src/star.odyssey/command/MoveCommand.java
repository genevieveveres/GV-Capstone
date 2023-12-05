package star.odyssey.command;

import star.odyssey.character.Entity;
import star.odyssey.game.Game;
import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;
import star.odyssey.location.Location;
import star.odyssey.ui.MainMenu;

import java.util.Map;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class MoveCommand implements Command {
    private final GameState gameState;

    String gameTxtFilePath = "./data/gameText.json";
    private final Map<String, String> moveTxtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "move_cmd");


    public MoveCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String direction) {
        Entity player = gameState.getPlayer();

        if (direction == null || direction.isEmpty()) {
            return moveTxtMap.get("direction_null");
        }

        Location currentLocation = player.getLocation();
        Location nextLocation = currentLocation.getConnections().get(direction);

        boolean nextIsEngine =  nextLocation.getIndex().equals("ship_engine");
        boolean hasStarstone = player.getInventory().contains(gameState.getItemManager().getItem("starstone"));

        if (nextLocation == null) {
            return moveTxtMap.get("direction_unknown");
        } else if (nextIsEngine && hasStarstone) {
            clearScreen();
            System.out.println(wrapText(GameUtil.jsonToString(gameTxtFilePath, "win_repair_engine")));
            pauseDisplay();
            Game.stop();
            clearScreen();
            MainMenu.execute();
            return null;
        } else {
            player.setLocation(nextLocation);
            player.getLocation().setVisited(true);
            return moveTxtMap.get("move_success") + nextLocation.getName();
        }
    }
}
