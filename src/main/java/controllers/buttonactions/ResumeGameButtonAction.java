package controllers.buttonactions;

import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class ResumeGameButtonAction implements IButtonAction{

    Game game;
    IPanelState currentState;
    EPanelState panelState;

    public ResumeGameButtonAction(EPanelState panelState, IPanelState currentState, Game game) {
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
