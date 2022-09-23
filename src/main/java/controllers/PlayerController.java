package controllers;

import model.Game;
import model.gameobjects.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PlayerController implements KeyListener {
    private final Player player;
    private boolean spaceKeyDown;
    private List<EDirection> pressedOrder;
    private Game game;

    public PlayerController(Game game) {
        pressedOrder = new ArrayList<>();
        this.game = game;
        this.player = game.getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W && !pressedOrder.contains(EDirection.UP)) {
            pressedOrder.add(EDirection.UP);
        }
        if (code == KeyEvent.VK_A && !pressedOrder.contains(EDirection.LEFT)) {
            pressedOrder.add(EDirection.LEFT);
        }
        if (code == KeyEvent.VK_S && !pressedOrder.contains(EDirection.DOWN)) {
            pressedOrder.add(EDirection.DOWN);
        }
        if (code == KeyEvent.VK_D && !pressedOrder.contains(EDirection.RIGHT)) {
            pressedOrder.add(EDirection.RIGHT);
        }

        if (!pressedOrder.isEmpty())
            player.setDirection(pressedOrder.get(pressedOrder.size()-1));

        if (code == KeyEvent.VK_SPACE) {
            if (!spaceKeyDown) {
                spaceKeyDown = true;
                game.setSpacePressed();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                pressedOrder.remove(EDirection.UP);
            }
            case (KeyEvent.VK_A) -> {
                pressedOrder.remove(EDirection.LEFT);
            }
            case (KeyEvent.VK_S) -> {
                pressedOrder.remove(EDirection.DOWN);
            }
            case (KeyEvent.VK_D) -> {
                pressedOrder.remove(EDirection.RIGHT);
            }
            case (KeyEvent.VK_SPACE) -> {
                spaceKeyDown = false;
            }
        }
        if (pressedOrder.isEmpty())
            player.setDirection(EDirection.NOT_MOVING);
        else
            player.setDirection(pressedOrder.get(pressedOrder.size()-1));
    }

}