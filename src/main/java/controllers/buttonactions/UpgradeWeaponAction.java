package controllers.buttonactions;

import model.helperclasses.ShopTransaction;

public class UpgradeWeaponAction implements IButtonAction {

    private ShopTransaction shopTransaction;
    public UpgradeWeaponAction(ShopTransaction shopTransaction){
        this.shopTransaction = shopTransaction;

    }
    @Override
    public void performAction() {
        shopTransaction.upgradeWeapon();
    }
}
