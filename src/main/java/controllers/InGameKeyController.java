package controllers;
import model.gameinterfaces.IGame;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// In the class diagram this class is named "KeyClickedController" as it had that name before!
/**
 * This class is responsible for different events that can happen ingame depending on user input.
 * As of now it can pause by clicking escape and enter the shop by clicking enter while player is on shop.
 * @author Arthur Alexandersson, Gustav Gille
 */
public class InGameKeyController implements KeyListener {
    private final IGame game;
    private final IPanelState panel;
    private boolean enterKeyDown;
    private boolean escapeKeyDown;

    /**
     * Instantiates an InGameEventController
     * @param game model class to handle
     * @param panel current panel state
     */
    public InGameKeyController(IGame game, IPanelState panel) {
        this.game = game;
        this.panel = panel;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_ENTER) -> {
                if (!enterKeyDown) {
                    enterKeyDown = true;
                    if (game.playerOnShop()){
                        panel.changePanelState(EPanelState.SHOP);
                        game.pause();
                    }
                }
            }
            case (KeyEvent.VK_ESCAPE) -> {
                if (!escapeKeyDown) {
                    escapeKeyDown = true;
                    game.pause();
                    panel.changePanelState(EPanelState.PAUSE);
                }
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
            case (KeyEvent.VK_ENTER) -> {
                enterKeyDown = false;
            }
            case (KeyEvent.VK_ESCAPE) -> {
                escapeKeyDown = false;
            }
        }
    }
}
