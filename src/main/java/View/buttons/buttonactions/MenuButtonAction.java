package View.buttons.buttonactions;

import View.panelstates.EPanelState;
import View.panelstates.IPanelState;

public class MenuButtonAction implements  IButtonAction{

    private EPanelState panelState;
    private IPanelState currentState;

    public MenuButtonAction(EPanelState panelState, IPanelState currentState){
        this.panelState = panelState;
        this.currentState = currentState;
    }

    @Override
    public void performAction() {
        currentState.changePanelState(panelState);

    }
}
