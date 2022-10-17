package controllers.buttonactions;

import model.gameinterfaces.ICreateGame;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

/**
 * Class used by buttons which creates a new game.
 */
public class NewGameButtonAction implements IButtonAction {
    private ICreateGame createGame;
    private IPanelState currentState;
    private EPanelState panelState;

    /**
     * NewGameButtonAction constructor.
     * @param panelState new panelState.
     * @param currentState current panelState.
     * @param createGame used to create a new game when the button is pressed.
     */
    public NewGameButtonAction(EPanelState panelState, IPanelState currentState, ICreateGame createGame) {
        this.createGame = createGame;
        this.currentState = currentState;
        this.panelState = panelState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        createGame.newGame();
        currentState.changePanelState(panelState);
    }
}
