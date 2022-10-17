package sound;

import javax.imageio.IIOException;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    public void setSoundFile(String soundFileName){
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    private void stop() {
        if(clip.isRunning()) clip.stop();
    }

}
