package controllers.buttonactions;

import model.gameinterfaces.ICanPause;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

/**
 * ResumeGameButtonAction is used by buttons, which when activated, that resume to game from paused state.
 * @author Herman Noren, Arthur Alexandersson
 */
public class ResumeGameButtonAction implements IButtonAction{

    private final ICanPause game;
    private final IPanelState currentState;
    private final EPanelState panelState;

    /**
     * Instantiates a ResumeGameButtonAction
     * @param panelState new panelState
     * @param currentState the current panelState
     * @param canPause of type ICanPause, interface with methods used for pause/resume
     */
    public ResumeGameButtonAction(EPanelState panelState, IPanelState currentState, ICanPause canPause) {
        this.game = canPause;
        this.currentState = currentState;
        this.panelState = panelState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        game.resume();
        currentState.changePanelState(panelState);
    }
}
