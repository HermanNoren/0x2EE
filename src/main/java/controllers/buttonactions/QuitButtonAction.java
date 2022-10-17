package controllers.buttonactions;

public class QuitButtonAction implements IButtonAction{


    @Override
    public void performAction() {
        System.exit(0);
    }
}
