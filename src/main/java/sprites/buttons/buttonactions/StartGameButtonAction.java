package sprites.buttons.buttonactions;

import gamestates.InGameState;
import main.Game;

import java.io.IOException;

public class StartGameButtonAction implements ButtonAction{

    private Game game;

    public StartGameButtonAction(Game game) {
        this.game = game;
    }

    @Override
    public void performAction() {
        game.setState(new InGameState(game));
    }
}
