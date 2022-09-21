package model.gamestates;

import model.Game;
import view.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class MainMenuState implements IGameState {
    private final EPanelState stateTag = EPanelState.MAINMENU;

    public MainMenuState() {
    }

    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    @Override
    public void updateButtons() {

    }

    @Override
    public void update() {
    }
}
