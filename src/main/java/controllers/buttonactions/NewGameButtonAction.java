package controllers.buttonactions;

import model.gameinterfaces.ICreateGame;
import view.panelstates.EPanelState;
import view.panelstates.IPanelState;

/**
 * Class used by buttons which creates a new game.
 * @author Arthur Alexandersson, Herman Norén
 */
public class NewGameButtonAction implements IButtonAction {
    private final ICreateGame createGame;
    private final IPanelState currentState;
    private final EPanelState panelState;

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
        createGame.newGameRound();
        currentState.changePanelState(panelState);
    }
}
