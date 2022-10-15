package controllers.buttonactions;

import model.helperclasses.TransactionHandler;

public class UpgradeWeaponAction implements IButtonAction {

    private TransactionHandler transactionHandler;
    public UpgradeWeaponAction(TransactionHandler transactionHandler){
        this.transactionHandler = transactionHandler;

    }
    @Override
    public void performAction() {
        transactionHandler.upgradeWeapon();
    }
}
