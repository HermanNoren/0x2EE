package controllers.buttonactions;

import model.Game;
import model.gameinterfaces.INewGamable;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class NewGameButtonAction implements IButtonAction {

    INewGamable game;
    IPanelState currentState;
    EPanelState panelState;

    public NewGameButtonAction(EPanelState panelState, IPanelState currentState, INewGamable game) {
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
