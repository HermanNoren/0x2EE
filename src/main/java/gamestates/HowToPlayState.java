package gamestates;

import main.Game;
import sprites.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HowToPlayState implements  IGameState{

    private Game game;
    private EPanelState stateTag = EPanelState.HOWTOPLAY;

    private ArrayList<GameButton> buttons;

    public HowToPlayState(Game game){
        this.game = game;
        buttons = game.getBackButtons();

    }
    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    @Override
    public void update() {
        if (game.getEnterPressed()){
            buttons.get(0).isClicked();
        }
    }
}
