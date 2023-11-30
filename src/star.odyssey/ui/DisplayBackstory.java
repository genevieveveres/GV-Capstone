package star.odyssey.ui;

import star.odyssey.game.GameUtil;

import static star.odyssey.ui.ConsoleDisplayUtils.clearScreen;
import static star.odyssey.ui.ConsoleDisplayUtils.pauseDisplay;
import static star.odyssey.ui.ConsoleDisplayUtils.wrapText;

public class DisplayBackstory {
    public static void displayBackstory() {
        String file = "./data/gameText.json";
        clearScreen();
        String backstory = GameUtil.jsonToString(file, "backstory");
        System.out.println(wrapText(backstory));
        pauseDisplay();
        clearScreen();
    }

    public static void main(String[] args) {
        displayBackstory();
    }
}