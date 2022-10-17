package controllers.buttonactions;

import model.gameinterfaces.ICreateGame;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class NewGameButtonAction implements IButtonAction {

    ICreateGame game;
    IPanelState currentState;
    EPanelState panelState;

    public NewGameButtonAction(EPanelState panelState, IPanelState currentState, ICreateGame game) {
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
