package star.odyssey.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class HealCommandTest extends BaseTest {
    @Test
    public void userHealthGoesUp_WhenUsingHeal(){
        testCommand = new HealCommand(testGameState);
        assertEquals(100,testGameState.getPlayer().getHealth());
        testCommand.execute("");
        assertEquals(110,testGameState.getPlayer().getHealth());
    }

    // test if passing the name "alexa all knowing" will pass the validation test
    @Test
    public void testExecute_ShouldPass_WhenAPlayerIsCreated(){
        testCommand = new HealCommand(testGameState);
        assertEquals(100,testGameState.getPlayer().getHealth());
        testCommand.execute("alexa all knowing");
        assertEquals(110,testGameState.getPlayer().getHealth());
    }

    @Test
    public void testExecute_ShouldFail_WhenADifferentNameIsPassed(){
        testCommand = new HealCommand(testGameState);
        assertEquals(100,testGameState.getPlayer().getHealth());
        testCommand.execute("alexa");
        assertEquals(100,testGameState.getPlayer().getHealth());
    }
}