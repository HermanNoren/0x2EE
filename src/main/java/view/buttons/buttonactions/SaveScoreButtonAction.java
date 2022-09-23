package view.buttons.buttonactions;

import model.Game;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

public class SaveScoreButtonAction implements IButtonAction {

    private EPanelState panelState;
    private IPanelState currentState;

    public SaveScoreButtonAction(EPanelState panelState, IPanelState currentState){
        this.panelState = panelState;
        this.currentState = currentState;
    }

    @Override
    public void performAction() {
        currentState.changePanelState(panelState);
    }
}
