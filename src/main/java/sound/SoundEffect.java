package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private Clip clip;

    private AudioInputStream sound;

    public SoundEffect(String soundFileName) {
        setSoundFile(soundFileName);
    }

    private void setSoundFile(String soundFileName){
        try {
            File soundFile = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        //stop();
        clip.setFramePosition(0);
        clip.start();
    }

    private void stop() {
        if(clip.isRunning()) clip.stop();
    }
}
