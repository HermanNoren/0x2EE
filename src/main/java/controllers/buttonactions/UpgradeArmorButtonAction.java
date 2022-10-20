package controllers.buttonactions;

import model.helperclasses.TransactionHandler;

/**
 * UpgradeArmorButtonAction is used by buttons, which when activated, upgrades the players armor
 * @author Gustav Gille
 */
public class UpgradeArmorButtonAction implements IButtonAction{
    private final TransactionHandler transactionHandler;

    /**
     * Instantiates an UpgradeArmorButtonAction
     * @param transactionHandler TransactionHandler to handle the transaction logic
     */
    public UpgradeArmorButtonAction(TransactionHandler transactionHandler){
            this.transactionHandler = transactionHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        transactionHandler.upgradeArmor();
    }
}
