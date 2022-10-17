package controllers;

import model.gameinterfaces.IHasHighscore;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HighscoreController implements KeyListener {


    private IHasHighscore game;

    private boolean enterKeyDown;


    public HighscoreController(IHasHighscore game) {
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
                // Only accept letters and numbers
                if ((code >= 65 && code <= 90) || (code >= 48 && code <=57)) {
                    game.updateName(String.valueOf(e.getKeyChar()).toUpperCase());
                }
            }

        }

    }
}
