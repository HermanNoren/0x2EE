package controllers;

import model.gameinterfaces.IHasHighscore;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * HighscoreController handles keyboard input when entering name / nickname when saving highscore
 * @author Rickard Leksell
 */
public class HighscoreController implements KeyListener {

    private IHasHighscore game;

    /**
     * Instantiates a HighscoreController
     * @param game object that has a savable highscore
     */
    public HighscoreController(IHasHighscore game) {
        this.game = game;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_BACK_SPACE) -> {
                game.deleteLetter();
            }
            default -> {
                // Only accept letters and numbers
                if ((code >= 65 && code <= 90) || (code >= 48 && code <= 57)) {
                    game.updateName(String.valueOf(e.getKeyChar()).toUpperCase());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
