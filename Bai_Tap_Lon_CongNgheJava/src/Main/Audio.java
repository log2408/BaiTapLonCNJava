
package Main;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio extends Thread {
    private String filePath;
    private Clip clip;

    public Audio(String filePath) {
        this.filePath = filePath;
    }
    public void run() {
        try {
            File file = new File(filePath).getAbsoluteFile();
            AudioInputStream aui = AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(aui);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopAudio() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

