package controllers;
import model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyClickedController implements KeyListener {
    private final Game game;
    private boolean wKeyDown;
    private boolean sKeyDown;
    private boolean enterKeyDown;
    private boolean escapeKeyDown;
    private boolean spaceKeyDown;

    public KeyClickedController(Game game) {
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
                if (!wKeyDown) {
                    wKeyDown = true;
                    game.setWPressed();
                }
            }
            case (KeyEvent.VK_S) -> {
                if (!sKeyDown) {
                    sKeyDown = true;
                    game.setSPressed();
                }

            }
            case (KeyEvent.VK_ENTER) -> {
                if (!enterKeyDown) {
                    enterKeyDown = true;
                    game.setEnterPressed();
                }

            }
            case (KeyEvent.VK_ESCAPE) -> {
                if (!escapeKeyDown) {
                    escapeKeyDown = true;
                    game.setEscapePressed();
                }
            }
            case (KeyEvent.VK_SPACE) -> {
                if (!spaceKeyDown) {
                    spaceKeyDown = true;
                    game.setSpacePressed();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                wKeyDown = false;
                game.resetWPressed();
            }
            case (KeyEvent.VK_S) -> {
                sKeyDown = false;
                game.resetSPressed();
            }
            case (KeyEvent.VK_ENTER) -> {
                enterKeyDown = false;
                game.resetEnterPressed();
            }
            case (KeyEvent.VK_ESCAPE) -> {
                escapeKeyDown = false;
                game.resetEscapePressed();
            }
            case (KeyEvent.VK_SPACE) -> {
                spaceKeyDown = false;
                game.resetSpacePressed();
            }
        }
    }
}
