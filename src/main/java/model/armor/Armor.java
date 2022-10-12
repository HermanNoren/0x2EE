package model.armor;

import model.gameobjects.IUpgradable;

public class Armor implements IArmor, IUpgradable {

    public Armor(){

    }

    private int currentLevel = 1;
    private int currentArmorPrize = 10;

    /**
     * @param damageTaken reduces damage
     * @return the damage that the armor absorbs
     */
    @Override
    public double damageReduction(int damageTaken) {
        double reduction = currentLevel/100;
        return damageTaken * reduction;
    }
    public double currentDamageReduction() {
        return currentLevel;
    }

    @Override
    public void levelUp() {
        currentLevel++;
        currentArmorPrize *= currentLevel;
    }

    @Override
    public int currentLevel() {
        return currentLevel;
    }

    @Override
    public int currentPrice() {
        return currentArmorPrize;
    }

    @Override
    public int statsIfUpgraded() {
        return currentLevel + 1;
    }

    @Override
    public int upgradeCost() {
        return currentArmorPrize * (currentLevel + 1);
    }
}
