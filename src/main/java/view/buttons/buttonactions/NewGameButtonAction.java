package view.buttons.buttonactions;

import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class NewGameButtonAction implements IButtonAction{

    Game game;
    IPanelState currentState;
    EPanelState panelState;

    public NewGameButtonAction(EPanelState panelState, IPanelState currentState, Game game) {
        this.game = game;
        this.currentState = currentState;
        this.panelState = panelState;
    }

    @Override
    public void performAction() {
        game.newGame();
        currentState.changePanelState(panelState);
    }
}
