package star.odyssey.sound;

import java.io.*;
import javax.sound.sampled.*;

public enum SoundEffect {
    MINING_LASER("data/audio/use_mining_laser.wav"),
    ORE("data/audio/use_ore.wav"),
    JOURNAL("data/audio/use_journal.wav"),
    SHIP_SCANNER("data/audio/use_ship_scanner.wav"),
    ELIXIR("data/audio/use_elixir.wav");


    private Clip clip;
    private static boolean soundEnabled = true;

    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    // Play the sound effect from the beginning
    public void play() {
        if (soundEnabled) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static void setSoundEnabled(boolean soundEnabled) {
        SoundEffect.soundEnabled = soundEnabled;
    }
}