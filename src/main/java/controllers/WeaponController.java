package controllers;

import sound.SoundEffect;
import model.gameinterfaces.IHasProjectiles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is responsible for controlling the players weapon depending on keyboard input
 * @author Kasper Ljunggren
 */
public class WeaponController implements KeyListener {

    private final IHasProjectiles game;
    private boolean spaceKeyDown;
    SoundEffect se = new SoundEffect();

    /**
     * Instantiates a WeaponController
     * @param game object that has projectiles
     */
    public WeaponController(IHasProjectiles game) {
        this.game = game;
        se.setSoundFile("src/main/resources/sound/laserShoot.wav");
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_SPACE) -> {
                if (!spaceKeyDown) {

                    //se.play();
                    spaceKeyDown = true;
                    game.makePlayerShoot();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case (KeyEvent.VK_SPACE) -> {
                spaceKeyDown = false;

            }
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
