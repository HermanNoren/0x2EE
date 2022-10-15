package controllers.buttonactions;

import model.gameinterfaces.IPausable;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class ResumeGameButtonAction implements IButtonAction{

    IPausable game;
    IPanelState currentState;
    EPanelState panelState;

    public ResumeGameButtonAction(EPanelState panelState, IPanelState currentState, IPausable game) {
        this.game = game;
        this.currentState = currentState;
        this.panelState = panelState;
    }

    @Override
    public void performAction() {
        game.resume();
        currentState.changePanelState(panelState);
    }
}
