package star.odyssey.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundAudioPlayer {

    private Clip clip;

    public BackgroundAudioPlayer(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}