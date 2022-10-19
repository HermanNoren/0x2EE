package controllers;

import controllers.sound.SoundEffect;
import model.gameinterfaces.IHasProjectiles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WeaponController implements KeyListener {

    private final IHasProjectiles game;
    private boolean spaceKeyDown;

    SoundEffect se = new SoundEffect();

    public WeaponController(IHasProjectiles game) {
        this.game = game;
        se.setSoundFile("sound/BKFL.wav");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_SPACE) -> {
                if (!spaceKeyDown) {

                    se.play();
                    spaceKeyDown = true;
                    game.makePlayerShoot();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case (KeyEvent.VK_SPACE) -> {
                spaceKeyDown = false;

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
