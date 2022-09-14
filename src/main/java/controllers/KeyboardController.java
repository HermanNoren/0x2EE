package controllers;

import gamestates.PauseState;
import main.Game;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyboardController implements KeyListener {
    private Game game;

    public KeyboardController(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> {
                game.setWPressed(true);
            }
            case (KeyEvent.VK_A) -> {
                game.setAPressed(true);
            }
            case (KeyEvent.VK_S) -> {
                game.setSPressed(true);
            }
            case (KeyEvent.VK_D) -> {
                game.setDPressed(true);
            }
            case (KeyEvent.VK_ENTER) -> {
                game.setEnterPressed(true);
            }
            case (KeyEvent.VK_ESCAPE) -> {
                game.setState(new PauseState(game));
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                game.setWPressed(false);
            }
            case (KeyEvent.VK_A) -> {
                game.setAPressed(false);
            }
            case (KeyEvent.VK_S) -> {
                game.setSPressed(false);
            }
            case (KeyEvent.VK_D) -> {
                game.setDPressed(false);
            }
            case (KeyEvent.VK_ENTER) -> {
                game.setEnterPressed(false);
            }
        }
    }
}
