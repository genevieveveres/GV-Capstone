package star.odyssey.ui;

import star.odyssey.game.GameUtil;
import star.odyssey.sound.BackgroundAudioPlayer;

import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class DisplaySplash {

    // Display splash screen
    public static void displaySplash() {
        String settingsFilePath = "./data/userSettings.json";
        BackgroundAudioPlayer backgroundAudioPlayer = new BackgroundAudioPlayer("data/audio/ambient_game_start.wav");
        backgroundAudioPlayer.setVolume(GameUtil.jsonToInt(settingsFilePath, "initial_volume"));
        backgroundAudioPlayer.loop();

        clearScreen();
        String gameTxtFilePath = "./data/gameText.json";

        String splashscreen = GameUtil.jsonToString(gameTxtFilePath, "splashscreen");
//        List<ColoredText> coloredTextList = new ArrayList<>(); // SWING
        for (char c : splashscreen.toCharArray()) {
            if (c == '█') {
                System.out.print(makeRed(String.valueOf(c)));
                // SWING
//                coloredTextList.add(new ColoredText(String.valueOf(c), TextColor.RED));
            } else if (c == '░') {
                System.out.print(makeBlue(String.valueOf(c)));
                // SWING
//                coloredTextList.add(new ColoredText(String.valueOf(c), TextColor.BLUE));
            } else {
                System.out.print(c);
                // SWING
//                coloredTextList.add(new ColoredText(String.valueOf(c)));
//                coloredTextList.add(new ColoredText(String.valueOf(c)));
            }
        }
//        SwingDisplayUtils.getInstance().displayText(coloredTextList);

        pauseDisplay();
        clearScreen();
        backgroundAudioPlayer.stop();
    }
}
