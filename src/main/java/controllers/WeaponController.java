package controllers;

import model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WeaponController implements KeyListener {

    private final Game game;
    private boolean spaceKeyDown;

    public WeaponController(Game game) {
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
