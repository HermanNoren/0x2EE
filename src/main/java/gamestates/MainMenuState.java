package gamestates;

import main.Game;
import buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class MainMenuState implements IGameState {
    private Game game;
    private int activePos;
    private boolean recentlyMovedUp;
    private boolean recentlyMovedDown;
    private ArrayList<GameButton> buttons;
    private final EPanelState stateTag = EPanelState.MAINMENU;

    public MainMenuState() {
        this.game = Game.getInstance();
        activePos = 0;
        recentlyMovedUp = false;
        recentlyMovedDown = false;
    }

    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    @Override
    public void updateButtons() {
        buttons = game.getMainMenuButtons();
    }

    @Override
    public void update() {

        if (game.getSPressed()) {
            game.resetSPressed();
            activePos += 1;
            activePos %= buttons.size();
        }

        if (game.getWPressed()) {
            game.resetWPressed();
            activePos -= 1;
            if (activePos < 0) {
                activePos = buttons.size() - 1;
            }
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setIsSelected(i == activePos);
        }

        if (game.getEnterPressed()) {
            game.resetEnterPressed();
            buttons.get(activePos).isClicked();
        }
    }
}
