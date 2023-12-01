package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.game.GameUtil;
import star.odyssey.location.Location;
import star.odyssey.sound.BackgroundAudioPlayer;

import java.util.Map;

public class MoveCommand implements Command {
    private final GameState gameState;
    private BackgroundAudioPlayer backgroundAudioPlayer;

    String gameTxtFilePath = "./data/gameText.json";
    private Map<String, String> txtMap = GameUtil.jsonToStringMap(gameTxtFilePath, "move_cmd");

    public MoveCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String direction) {
        if (direction == null || direction.isEmpty()) {
            return txtMap.get("direction_null");
        }

        Location currentLocation = gameState.getPlayer().getLocation();
        Location nextLocation = currentLocation.getConnections().get(direction);

        if (nextLocation == null) {
            return txtMap.get("direction_unknown");
        } else {
            gameState.getPlayer().setLocation(nextLocation);
            return txtMap.get("move_success") + nextLocation.getName();
        }
    }
}
