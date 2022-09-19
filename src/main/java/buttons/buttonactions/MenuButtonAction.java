package buttons.buttonactions;

import gamestates.HighscoreState;
import gamestates.IGameState;
import gamestates.InGameState;
import main.Game;
import view.panelstates.IPanelState;

public class MenuButtonAction implements  IButtonAction{

    private IGameState gameState;
    private Game game;

    public MenuButtonAction(IGameState gameState){
        this.gameState = gameState;
        this.game = Game.getInstance();
    }

    @Override
    public void performAction() {
        game.setState(gameState);

    }
}
