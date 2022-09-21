package model.gamestates;

import model.Game;
import view.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HowToPlayState implements IGameState{
    private EPanelState stateTag = EPanelState.HOWTOPLAY;

    public HowToPlayState(){
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
