package controllers;

import View.buttons.GameButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ButtonController implements KeyListener {

    private int activePos;
    private final List<GameButton> buttons;
    private boolean wKeyDown, sKeyDown, enterKeyDown, escapeKeyDown, wClicked, sClicked, enterClicked, escapeClicked;

    public ButtonController(List<GameButton> buttons) {
        this.buttons = buttons;
        activePos = 0;
        wKeyDown = false;
        sKeyDown = false;
        enterKeyDown = false;
        escapeKeyDown = false;
        wClicked = false;
        sClicked = false;
        enterClicked = false;
        escapeClicked = false;
    }

    public void update() {

        if (sClicked) {
            sClicked = false;
            activePos += 1;
            activePos %= buttons.size();
        }

        if (wClicked) {
            wClicked = false;
            activePos -= 1;
            if (activePos < 0) {
                activePos = buttons.size() - 1;
            }
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIsSelected(i == activePos);
        }

        if (enterClicked) {
            enterClicked = false;
            buttons.get(activePos).isClicked();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
            case (KeyEvent.VK_ESCAPE) -> {
                if (!escapeKeyDown) {
                    escapeKeyDown = true;
                    escapeClicked = true;
                }
            }
        }
    }

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
            case (KeyEvent.VK_ESCAPE) -> {
                escapeKeyDown = false;
            }
        }
    }
}
