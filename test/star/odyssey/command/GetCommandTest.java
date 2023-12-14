package star.odyssey.command;

import org.junit.Before;
import org.junit.Test;
import star.odyssey.character.EntityManager;
import star.odyssey.game.GameState;
import star.odyssey.inventory.ItemManager;
import star.odyssey.location.LocationManager;

import static org.junit.Assert.*;

public class GetCommandTest extends BaseTest {

    @Test
    public void userCanGetItem_WhenItemIsInRoom(){
        testCommand = new GetCommand(testGameState);
        Command goCommand = new MoveCommand(testGameState);
        //assertEquals(0, testGameState.getPlayer().getInventory().size());
        testCommand.execute("gravity hammer");
        assertEquals(1,testGameState.getPlayer().getInventory().size());
    }

    @Test
    public void testExecute_ShouldFail_WhenPassedAnInvalidItemName(){
        testCommand = new GetCommand(testGameState);
        assertEquals("It seems like you're trying to get something, but what exactly?",testCommand.execute(null));
    }
}