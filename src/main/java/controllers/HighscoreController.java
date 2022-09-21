package controllers;

import model.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class HighscoreController implements KeyListener {


    private Game game;

    private ArrayList<String> name;

    private boolean enterKeyDown;


    public HighscoreController() {
        this.game = Game.getInstance();
        name = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case (KeyEvent.VK_ENTER) -> {
                if (!enterKeyDown){
                    enterKeyDown = true;
                    game.setEnterPressed();
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case (KeyEvent.VK_ENTER) -> {
                enterKeyDown = false;
                game.resetEnterPressed();
            }
            case (KeyEvent.VK_BACK_SPACE) ->{
                 game.deleteLetter();
            }
            default -> {
                    game.updateName(String.valueOf(e.getKeyChar()).toUpperCase());


            }

        }

    }
}
