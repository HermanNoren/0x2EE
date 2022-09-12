package gamestates;

import main.Game;
import sprites.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class MainMenuState implements IGameState {
    private Game game;
    private int activePos;
    private boolean recentlyMovedUp;
    private boolean recentlyMovedDown;
    private final ArrayList<GameButton> buttons;
    private final EPanelState stateTag = EPanelState.MAINMENU;

    public MainMenuState(Game game) {
        this.game = game;
        activePos = 0;
        recentlyMovedUp = false;
        recentlyMovedDown = false;

        buttons = game.getMainMenuButtons();
    }

    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

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
