package sprites.buttons.buttonactions;

import gamestates.InGameState;
import main.Game;

public class StartGameButtonAction implements IButtonAction {

    private Game game;

    public StartGameButtonAction(Game game) {
        this.game = game;
    }

    @Override
    public void performAction() {
        game.setState(new InGameState(game));
    }
}
