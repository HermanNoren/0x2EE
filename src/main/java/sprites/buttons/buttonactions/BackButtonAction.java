package sprites.buttons.buttonactions;

import gamestates.MainMenuState;
import main.Game;

public class BackButtonAction implements IButtonAction{

    private Game game;

    public BackButtonAction(Game game){
        this.game = game;
    }
    @Override
    public void performAction() { game.setState(new MainMenuState(game));
    }
}
