package gamestates;

import main.Game;
import buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HighscoreState implements IGameState {

    private final Game game;
    private final EPanelState stateTag = EPanelState.HIGHSCORES;

    private  ArrayList<GameButton> buttons;

    public HighscoreState(){
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
