package star.odyssey.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private static Clip clip;

    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void changeAudioFile(String filePath) {
        if (clip != null && clip.isOpen()) {
            clip.stop();
            clip.close();
        }
        playAudio(filePath);
    }

    public static void stopAudio() {
        if (clip != null && clip.isOpen()) {
            clip.stop();
        }
    }

    public static void resumeAudio() {
        if (clip != null && !clip.isActive()) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // METHODS
    public static void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public static void setVolume(int volume) {
        float volFloat = (float) volume / 100;
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            float gain = (volFloat * (max - min)) + min;
            gainControl.setValue(gain);
        }
    }
}
