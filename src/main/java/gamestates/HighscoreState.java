package gamestates;

import main.Game;
import sprites.buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HighscoreState implements IGameState {

    private Game game;
    private final EPanelState stateTag = EPanelState.HIGHSCORES;

    private ArrayList<GameButton> buttons;

    public HighscoreState(Game game){
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
