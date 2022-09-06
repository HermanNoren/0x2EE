package controllers;

import main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {
    private Game game;

    public KeyboardController(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) throws RuntimeException {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> {
                try {
                    game.getPlayer().move(Direction.UP);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            case (KeyEvent.VK_A) -> {
                try {
                    game.getPlayer().move(Direction.LEFT);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
            case (KeyEvent.VK_S) -> {
                try {
                    game.getPlayer().move(Direction.DOWN);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            case (KeyEvent.VK_D) -> {
                try {
                    game.getPlayer().move(Direction.RIGHT);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                try {
                    game.getPlayer().move(Direction.NOT_MOVING);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            case KeyEvent.VK_A:
                try {
                    game.getPlayer().move(Direction.NOT_MOVING);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            case KeyEvent.VK_S:
                try {
                    game.getPlayer().move(Direction.NOT_MOVING);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            case KeyEvent.VK_D:
                try {
                    game.getPlayer().move(Direction.NOT_MOVING);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

        }
    }
}
