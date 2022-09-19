package buttons.buttonactions;

import main.Game;

public class QuitButtonAction implements IButtonAction{

    public QuitButtonAction(){
    }

    @Override
    public void performAction() {
        System.exit(0);
    }
}
