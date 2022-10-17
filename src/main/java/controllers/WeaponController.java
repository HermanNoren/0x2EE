package controllers;

import sound.SoundEffect;
import model.gameinterfaces.IShootable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WeaponController implements KeyListener {

    private final IShootable game;
    private boolean spaceKeyDown;


    public WeaponController(IShootable game) {
        this.game = game;
    }

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
