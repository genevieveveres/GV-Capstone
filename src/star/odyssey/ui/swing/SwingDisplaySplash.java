package star.odyssey.ui.swing;

import star.odyssey.game.GameUtil;
import star.odyssey.sound.AudioPlayer;
import star.odyssey.sound.BackgroundAudioPlayer;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;

import java.util.ArrayList;
import java.util.List;

//import static star.odyssey.ui.ConsoleDisplayUtils.*;

public class SwingDisplaySplash {

    private static BackgroundAudioPlayer backgroundAudioPlayer;

    // Display splash screen
    public static void displaySplash() {

        // activate the audio player with the intro soundtrack
        AudioPlayer.changeAudioFile("data/audio/ambient_spaceship_entry.wav");
        AudioPlayer.playAudio("data/audio/ambient_spaceship_entry.wav");

        SwingDisplayUtils.clearScreen();
        String gameTxtFilePath = "./data/gameText.json";

        String splashscreen = GameUtil.jsonToString(gameTxtFilePath, "splashscreen");
        List<ColoredText> coloredTextList = new ArrayList<>(); // SWING
        for (char c : splashscreen.toCharArray()) {
            if (c == '█') {
                coloredTextList.add(new ColoredText(String.valueOf(c), TextColor.RED));
            } else if (c == '░') {
                coloredTextList.add(new ColoredText(String.valueOf(c), TextColor.BLUE));
            } else {
                // SWING
//                coloredTextList.add(new ColoredText(String.valueOf(c)));
                coloredTextList.add(new ColoredText(String.valueOf(c)));
            }
        }
        SwingDisplayUtils.getInstance().displayTextNl(coloredTextList, null);

        SwingDisplayUtils.pauseDisplay(SwingMainMenu::execute);
//
//        pauseDisplay();
//        clearScreen();
    }

    // returns the intro audio player so that it can ge modified such as turned off, muted, or the volume changed.
    public static BackgroundAudioPlayer getIntroAudioPlayer(){
        return backgroundAudioPlayer;
    }
}
