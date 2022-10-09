package controllers;
import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyClickedController implements KeyListener {
    private final Game game;
    private final IPanelState panel;
    private boolean enterKeyDown;
    private boolean escapeKeyDown;

    public KeyClickedController(Game game, IPanelState panel) {
        this.game = game;
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_ENTER) -> {
                if (!enterKeyDown) {
                    enterKeyDown = true;
                    if (game.getPlayer().isOnShop){
                        panel.changePanelState(EPanelState.SHOP);
                    }
                }
            }
            case (KeyEvent.VK_ESCAPE) -> {
                if (!escapeKeyDown) {
                    escapeKeyDown = true;
                    panel.changePanelState(EPanelState.PAUSE);
                }

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_ENTER) -> {
                enterKeyDown = false;
            }
            case (KeyEvent.VK_ESCAPE) -> {
                escapeKeyDown = false;
            }
        }
    }
}
