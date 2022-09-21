package model.gamestates;

import model.Game;
import view.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class PauseState implements IGameState{
    private final EPanelState stateTag = EPanelState.PAUSE;

    public PauseState(){
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
