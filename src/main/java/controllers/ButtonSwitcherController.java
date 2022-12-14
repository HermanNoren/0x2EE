package controllers;

import view.buttons.GameButton;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * This class provides the ability to use GameButtons with keyboard-only mechanics. Any view-class that uses buttons
 * should use this controller to provide navigation.
 * @author Herman Noren
 */
public class ButtonSwitcherController implements KeyListener {
    private int activePos;
    private final List<GameButton> stringButtons;
    private boolean wKeyDown, sKeyDown, enterKeyDown, wClicked, sClicked, enterClicked;

    /**
     * Instantiates a ButtonSwitcherController
     * @param buttons list of GameButtons to handle
     */
    public ButtonSwitcherController(List<GameButton> buttons) {
        this.stringButtons = buttons;
        activePos = 0;
        wKeyDown = false;
        sKeyDown = false;
        enterKeyDown = false;
        wClicked = false;
        sClicked = false;
        enterClicked = false;
    }

    /**
     * Updates the current state of buttons that are being handled.
     */
    public void update() {

        if (sClicked) {
            sClicked = false;
            activePos += 1;
            activePos %= stringButtons.size();
        }

        if (wClicked) {
            wClicked = false;
            activePos -= 1;
            if (activePos < 0) {
                activePos = stringButtons.size() - 1;
            }
        }

        for (int i = 0; i < stringButtons.size(); i++) {
            stringButtons.get(i).setIsSelected(i == activePos);
        }

        if (enterClicked) {
            enterClicked = false;
            stringButtons.get(activePos).isClicked();
        }
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
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_W) -> {
                if (!wKeyDown) {
                    wKeyDown = true;
                    wClicked = true;
                }
            }
            case (KeyEvent.VK_S) -> {
                if (!sKeyDown) {
                    sKeyDown = true;
                    sClicked = true;
                }

            }
            case (KeyEvent.VK_ENTER) -> {
                if (!enterKeyDown) {
                    enterKeyDown = true;
                    enterClicked = true;
                }

            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_W) -> {
                wKeyDown = false;
            }
            case (KeyEvent.VK_S) -> {
                sKeyDown = false;
            }
            case (KeyEvent.VK_ENTER) -> {
                enterKeyDown = false;
            }
        }
    }
}
