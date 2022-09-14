package controllers;

import main.Game;
import sprites.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private final Game game;
    private final Player player;

    public PlayerController(Game game) {
        this.game = game;
        this.player = game.getPlayer();

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> {
                player.setDirection(EDirection.UP);
            }
            case (KeyEvent.VK_A) -> {
                player.setDirection(EDirection.LEFT);
            }
            case (KeyEvent.VK_S) -> {
                player.setDirection(EDirection.DOWN);
            }
            case (KeyEvent.VK_D) -> {
                player.setDirection(EDirection.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                player.setDirection(EDirection.NOT_MOVING);
            }
            case (KeyEvent.VK_A) -> {
                player.setDirection(EDirection.NOT_MOVING);
            }
            case (KeyEvent.VK_S) -> {
                player.setDirection(EDirection.NOT_MOVING);
            }
            case (KeyEvent.VK_D) -> {
                player.setDirection(EDirection.NOT_MOVING);
            }
        }
    }

}