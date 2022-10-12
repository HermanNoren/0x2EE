package controllers.buttonactions;

import model.helperclasses.ShopTransaction;

public class UpgradeArmorButton implements IButtonAction{
    private ShopTransaction shopTransaction;

    public UpgradeArmorButton(ShopTransaction shopTransaction){
            this.shopTransaction = shopTransaction;
    }
    @Override
    public void performAction() {
        shopTransaction.upgradeArmor();
    }
}
