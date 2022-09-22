package view.buttons.buttonactions;

import model.gamestates.IGameState;
import model.Game;

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
