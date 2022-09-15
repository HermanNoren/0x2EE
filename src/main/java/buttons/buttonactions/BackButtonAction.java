package buttons.buttonactions;
import gamestates.MainMenuState;
import main.Game;

public class BackButtonAction implements IButtonAction{

    private Game game;

    public BackButtonAction(){

        this.game = Game.getInstance();
    }
    @Override
    public void performAction() { game.setState(new MainMenuState(game));
    }
}
