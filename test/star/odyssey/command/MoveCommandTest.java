package star.odyssey.command;

import org.junit.Before;
import org.junit.Test;
import star.odyssey.character.EntityManager;
import star.odyssey.game.GameState;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.LocationManager;

import static org.junit.Assert.*;

public class MoveCommandTest extends BaseTest {

    // test moving south to the ship's engine room from the start of the game
    @Test
    public void testExecute_ShouldDisplaySuccessfulMessage_WhenMovingSouth() {
        testCommand = new MoveCommand(testGameState);
        String result = testCommand.execute("south");
        assertEquals("You moved to Ship Engine Room", result);
    }

    // test moving north which should fail
    @Test
    public void testExecute_ShouldDisplayErrorMessage_WhenMovingNorth() {
        testCommand = new MoveCommand(testGameState);
        String result = testCommand.execute("north");
        assertEquals("You can't go that way.", result);
    }
}