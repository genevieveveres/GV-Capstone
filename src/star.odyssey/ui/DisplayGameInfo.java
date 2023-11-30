package star.odyssey.ui;

import star.odyssey.game.GameUtil;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;

public class DisplayGameInfo {

    public static void displayGameInfo() {
        String file = "./data/gameText.json";
        clearScreen();
        String gameInfo = GameUtil.jsonToString(file, "gameinfo");
        System.out.println(gameInfo);
        pauseDisplay();
        clearScreen();
    }
}