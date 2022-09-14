package gamestates;

import main.Game;
import sprites.buttons.GameButton;

import view.panelstates.EStateTag;

import java.util.ArrayList;

public class MainMenuState implements IGameState {
    private Game game;
    private int activePos;
    private boolean recentlyMovedUp;
    private boolean recentlyMovedDown;
    private final ArrayList<GameButton> buttons;
    private final EStateTag stateTag = EStateTag.MAINMENU;

    public MainMenuState(Game game) {
        this.game = game;
        activePos = 0;
        recentlyMovedUp = false;
        recentlyMovedDown = false;

        buttons = game.getMainMenuButtons();
    }

    /**
     * @return an enum containing the current statetag, being MAINMENU.
     */
    @Override
    public EStateTag getStateTag() {
        return stateTag;
    }

    /**
     * Updates the currently focused button on the main menu.
     */
    @Override
    public void update() {

        if (game.getSPressed() && !recentlyMovedDown) {
            recentlyMovedDown = true;
            activePos += 1;
            activePos %= buttons.size();
        }

        if (!game.getSPressed() && recentlyMovedDown) {
            recentlyMovedDown = false;
        }

        if (game.getWPressed() && !recentlyMovedUp) {
            recentlyMovedUp = true;
            activePos -= 1;
            if (activePos < 0) {
                activePos = buttons.size() - 1;
            }
        }

        if (!game.getWPressed() && recentlyMovedUp) {
            recentlyMovedUp = false;
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIsSelected(i == activePos);
        }

        if (game.getEnterPressed()) {
            buttons.get(activePos).isClicked();
        }
    }
}
