package controllers;

import model.gameobjects.Player;
import model.helperclasses.EDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PlayerController implements KeyListener {
    private final Player player;
    private boolean moving;
    private List<EDirection> pressedOrder;

    public PlayerController(Player player) {
        pressedOrder = new ArrayList<>();
        this.player = player;
    }

    private void setDirection(EDirection direction){
        if (!pressedOrder.contains(direction)) {
            pressedOrder.add(direction);
            player.setDirection(direction);
            moving = true;
        }
    }
    private void releaseDirection(EDirection direction){
        pressedOrder.remove(direction);
        if (pressedOrder.isEmpty()) {
            if (moving) {
                player.setDirection(EDirection.NOT_MOVING);
                moving = false;
            }
        }
        else
            player.setDirection(pressedOrder.get(pressedOrder.size()-1));
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> {
                setDirection(EDirection.UP);
            }
            case (KeyEvent.VK_A) -> {
                setDirection(EDirection.LEFT);
            }
            case (KeyEvent.VK_S) -> {
                setDirection(EDirection.DOWN);
            }
            case (KeyEvent.VK_D) -> {
                setDirection(EDirection.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                releaseDirection(EDirection.UP);
            }
            case (KeyEvent.VK_A) -> {
                releaseDirection(EDirection.LEFT);
            }
            case (KeyEvent.VK_S) -> {
                releaseDirection(EDirection.DOWN);
            }
            case (KeyEvent.VK_D) -> {
                releaseDirection(EDirection.RIGHT);
            }
            case (KeyEvent.VK_SPACE) -> {
            }
        }
    }
}