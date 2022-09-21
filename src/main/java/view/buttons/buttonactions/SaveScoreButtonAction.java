package view.buttons.buttonactions;

import model.Game;
import model.gamestates.IGameState;

public class SaveScoreButtonAction implements IButtonAction {

    private IGameState gameState;
    private Game game;

    public SaveScoreButtonAction(IGameState gameState){
        this.gameState = gameState;
        this.game = Game.getInstance();
    }

    @Override
    public void performAction() {
        game.updateHighscoreList();
        game.setState(gameState);
    }
}
