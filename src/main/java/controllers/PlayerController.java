package controllers;

import model.gameobjects.Player;
import model.gameobjects.EDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * This class is responsible for giving the player a direction to move depending on keyboard input
 * @author Kasper Ljunggren, Herman Noren
 */
public class PlayerController implements KeyListener {
    private final Player player;
    private boolean moving;
    private final List<EDirection> pressedOrder;

    /**
     * Instantiates a PlayerController
     * @param player player object to handle
     */
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

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * {@inheritDoc}
     *
     */
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

    /**
     * {@inheritDoc}
     *
     */
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
        }
    }
}