package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.location.Location;

public class MoveCommand implements Command {
    private final GameState gameState;

    public MoveCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public String execute(String direction) {
        if (direction == null || direction.isEmpty()) {
            return "You need to specify a direction to go.";
        }

        Location currentLocation = gameState.getPlayer().getLocation();
        Location nextLocation = currentLocation.getConnections().get(direction);

        if (nextLocation == null) {
            return "You can't go that way.";
        } else {
            gameState.getPlayer().setLocation(nextLocation);
            return "You moved to " + nextLocation.getName();
        }
    }
}
