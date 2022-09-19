package gamestates;

import buttons.GameButton;
import main.Game;
import view.panelstates.EPanelState;
import view.panelstates.NewHighscorePanelState;

import java.util.ArrayList;

public class NewHighscoreState implements IGameState {

    private final EPanelState stateTag = EPanelState.NEWHIGHSCORE;
    private Game game;

    private ArrayList<GameButton> buttons;

    public NewHighscoreState(){
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
            game.updateHighscoreList();
            buttons.get(0).isClicked();
        }
    }
}
