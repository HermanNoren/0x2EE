package controllers;

import main.Game;
import sprites.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private final Game game = Game.getInstance();
    private final Player player = game.getPlayer();

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            player.setDirection(EDirection.UP);
        }
        if(code == KeyEvent.VK_A){
            player.setDirection(EDirection.LEFT);
        }
        if(code == KeyEvent.VK_S){
            player.setDirection(EDirection.DOWN);
        }
        if(code == KeyEvent.VK_D){
            player.setDirection(EDirection.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            player.setDirection(EDirection.NOT_MOVING);
        }
        if(code == KeyEvent.VK_A){
            player.setDirection(EDirection.NOT_MOVING);
        }
        if(code == KeyEvent.VK_S){
            player.setDirection(EDirection.NOT_MOVING);
        }
        if(code == KeyEvent.VK_D){
            player.setDirection(EDirection.NOT_MOVING);
        }
    }

}

