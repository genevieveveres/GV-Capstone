package star.odyssey.command;

import star.odyssey.game.GameState;
import star.odyssey.location.Location;

public class MoveCommand implements Command {
    private final GameState gameState;

    public MoveCommand(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void execute(String direction) {
        if (direction == null || direction.isEmpty()) {
            System.out.println("You need to specify a direction to go.");
            return;
        }

        Location currentLocation = gameState.getPlayer().getLocation();
        Location nextLocation = currentLocation.getConnections().get(direction);

        if (nextLocation == null) {
            System.out.println("You can't go that way.");
        } else {
            gameState.getPlayer().setLocation(nextLocation);
        }
    }
}
