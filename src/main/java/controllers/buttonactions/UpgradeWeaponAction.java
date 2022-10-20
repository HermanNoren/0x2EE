package controllers.buttonactions;

import model.helperclasses.TransactionHandler;

/**
 * UpgradeWeaponAction is used by buttons, which when activated, upgrades the players weapon
 * @author Gustav Gille
 */
public class UpgradeWeaponAction implements IButtonAction {

    private final TransactionHandler transactionHandler;

    /**
     * Instantiates an UpgradeWeaponAction
     * @param transactionHandler TransactionHandler to handle the transaction logic
     */
    public UpgradeWeaponAction(TransactionHandler transactionHandler){
        this.transactionHandler = transactionHandler;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        transactionHandler.upgradeWeapon();
    }
}
