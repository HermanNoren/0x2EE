package gamestates;

import main.Game;
import buttons.GameButton;
import view.panelstates.EPanelState;

import java.util.ArrayList;

public class HighscoreState implements IGameState {

    private final Game game;
    private final EPanelState stateTag = EPanelState.HIGHSCORES;

    private final ArrayList<GameButton> buttons;

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
            game.resetEnterPressed();
            buttons.get(0).isClicked();
        }
    }
}
