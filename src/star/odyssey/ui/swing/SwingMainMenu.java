package star.odyssey.ui.swing;

import star.odyssey.game.GameManager;
import star.odyssey.game.GameUtil;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.ColoredTextLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static star.odyssey.ui.DisplayBackstory.displayBackstory;
import static star.odyssey.ui.DisplayGameInfo.displayGameInfo;

public class SwingMainMenu {

    public static boolean isInteger(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, String> readMainMenuFromJson() {
        Map<String, String> optionsMap;
        String gameTxtFilePath = "./data/gameText.json";

        optionsMap = GameUtil.jsonToStringMap(gameTxtFilePath, "main_menu");

        return optionsMap;
    }

    public static void execute(String input){
        execute();
    }

    public static void execute() {
        String settingsFilePath = "./data/userSettings.json";
        Map<String, String> optionsMap = readMainMenuFromJson();

        boolean validOption = false;
        int userOption = 0;


        //do {
        int optionCount = 0;
        List<ColoredText> coloredTextList = new ArrayList<>();
        coloredTextList.add(new ColoredTextLine(optionsMap.get("heading")));
        for (Map.Entry<String, String> option : optionsMap.entrySet()) {
            if (isInteger(option.getKey())) {
                coloredTextList.add(new ColoredTextLine(option.getKey() + ": " + option.getValue()));
                optionCount++;
            }
        }
        String promtString = "\n" + optionsMap.get("prompt") + optionCount + optionsMap.get("promptend");
        coloredTextList.add(new ColoredText(promtString));

        SwingDisplayUtils.getInstance().displayText(coloredTextList, SwingMainMenu::optionsSelected);

        //} while (!validOption);
    }

    private static void optionsSelected(String userInput){
        int userOption = 0;
        try {
            userOption = Integer.parseInt(userInput);
        }catch (Exception e){}
        GameManager gameManager = new GameManager();
        switch (userOption) {
            case 1:
                displayBackstory();
                displayGameInfo();
                //backgroundAudioPlayer.stop();
                gameManager.startGame(); // Starting a new game.
                //validOption = true;
                break;
            case 2:
                //backgroundAudioPlayer.stop();
                gameManager.loadSavedGame(); // Loading a previously saved game.
                //validOption = true;
                break;
            case 3:
                displayBackstory();
                displayGameInfo();
                //continue;
                break;
            case 4:
                System.out.println("Exiting..."); // Exiting the application.
                System.exit(0);
                break;
            default:
                // TODO FIX THIS
                //System.out.println(makeRed(optionsMap.get("invalid")));
                break;
        }
    }

}
