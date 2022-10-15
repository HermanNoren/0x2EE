package controllers;

import model.gameinterfaces.IHighscorable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HighscoreController implements KeyListener {


    private IHighscorable game;

    private boolean enterKeyDown;


    public HighscoreController(IHighscorable game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case (KeyEvent.VK_BACK_SPACE) ->{
                game.deleteLetter();
            }
            default -> {
                game.updateName(String.valueOf(e.getKeyChar()).toUpperCase());
            }

        }

    }
}
