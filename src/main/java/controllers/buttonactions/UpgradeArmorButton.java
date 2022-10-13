package controllers.buttonactions;

import model.helperclasses.TransactionHandler;

public class UpgradeArmorButton implements IButtonAction{
    private TransactionHandler transactionHandler;

    public UpgradeArmorButton(TransactionHandler transactionHandler){
            this.transactionHandler = transactionHandler;
    }
    @Override
    public void performAction() {
        transactionHandler.upgradeArmor();
    }
}
