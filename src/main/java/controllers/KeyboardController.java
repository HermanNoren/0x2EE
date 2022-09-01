package controllers;

import main.Game;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardController implements KeyListener {

    public boolean wPressed, aPressed, sPressed, dPressed;
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

        if(code == KeyEvent.VK_W){
            wPressed = true;
        }

        if(code == KeyEvent.VK_W){
            aPressed = true;
        }

        if(code == KeyEvent.VK_W){
            sPressed = true;
        }

        if(code == KeyEvent.VK_W){
            dPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("W");
                wPressed = false;
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                aPressed = false;
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                sPressed = false;
                break;
            case KeyEvent.VK_D:
                System.out.println("D");
                dPressed = false;
                break;
        }
    }
}
