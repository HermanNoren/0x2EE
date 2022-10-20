package controllers.buttonactions;

/**
 * QuitButtonAction used to terminate the program.
 * @author Rickard Leksell
 */
public class QuitButtonAction implements IButtonAction{
    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        System.exit(0);
    }
}
