package star.odyssey.sound;

import java.io.*;
import javax.sound.sampled.*;

public enum SoundEffect {
    MINING_LASER("data/audio/use_mining_laser.wav"),
    ORE("data/audio/use_ore.wav"),
    HEALING_ELIXIR("data/audio/use_potion.wav");

    public static enum Volume {
        OFF, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.OFF) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    // Optional static method to preload all the sound files.
    public static void init() {
        values(); // calls the constructor for all the elements
    }

    public static void main(String[] args) throws InterruptedException {
        init();
        MINING_LASER.play();
        Thread.sleep(2000);
        ORE.play();
        Thread.sleep(2000);
        HEALING_ELIXIR.play();
        Thread.sleep(2000);
    }
}