package controllers.buttonactions;

/**
 * QuitButtonAction used to terminate the program.
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
