package model.gamestates;

import view.buttons.GameButton;
import model.Game;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class NewHighscoreState implements IGameState {

    private final EPanelState stateTag = EPanelState.NEWHIGHSCORE;

    public NewHighscoreState() {}

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
