package controllers.sound;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameSound implements ActionListener {
    private SoundEffect soundEffect;
    private Timer timer;
    public InGameSound(){
        soundEffect = new SoundEffect();
        soundEffect.setSoundFile("src/main/resources/sound/nr15SONG.wav");
        timer = new Timer(1000,this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        soundEffect.play();
        timer.setDelay(31000);
    }

    public void run() {
        timer.start();
    }
}
