package controllers.buttonactions;

import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

/**
 * MenuButtonAction is used by the buttons in the menu. When a button in the menu is pressed,
 * the panelState is changed.
 * @author Kasper Ljunggren
 */
public class MenuButtonAction implements IButtonAction{
    private EPanelState panelState;
    private IPanelState currentState;

    /**
     * @param panelState the state to change to.
     * @param currentState the state to change from.
     */
    public MenuButtonAction(EPanelState panelState, IPanelState currentState){
        this.panelState = panelState;
        this.currentState = currentState;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        currentState.changePanelState(panelState);
    }
}
