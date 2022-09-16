package gamestates;

import main.Game;
import buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HowToPlayState implements IGameState{

    private Game game;
    private EPanelState stateTag = EPanelState.HOWTOPLAY;

    private ArrayList<GameButton> buttons;

    public HowToPlayState(){
        this.game = Game.getInstance();

    }
    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    @Override
    public void updateButtons() {
        buttons = game.getBackButtons();
    }

    @Override
    public void update() {
        if (game.getEnterPressed()){
            game.resetEnterPressed();
            buttons.get(0).isClicked();
        }
    }
}
