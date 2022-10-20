package controllers;

import controllers.sound.SoundEffect;
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


    /**
     * Instantiates a WeaponController
     * @param game object that has projectiles
     */
    public WeaponController(IHasProjectiles game) {
        this.game = game;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_SPACE) -> {
                if (!spaceKeyDown) {
                    spaceKeyDown = true;
                    game.makePlayerShoot();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     *
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
     *
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
