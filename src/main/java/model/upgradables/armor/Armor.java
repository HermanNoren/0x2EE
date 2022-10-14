package model.upgradables.armor;

import model.upgradables.IUpgradable;


public class Armor implements IArmor, IUpgradable  {
    private final double armorReductionConstant  = 0.08;
    private int currentLevel = 1;

    public Armor(){
    }

    /**
     * Method to determine how much damage reduction the player can receive,
     * determined by the player's level and an armor constant.
     * @param damageTaken the amount of damage taken
     * @return the reduced amount of damage taken
     */
    @Override
    public double damageReduction(int damageTaken) {
        return damageTaken - (currentLevel * armorReductionConstant);
    }

    @Override
    public void upgrade() {
        currentLevel++;
    }

    @Override
    public int statsIfUpgraded() {
        return currentLevel + 1;
    }

    @Override
    public int upgradeCost() {
        return currentLevel * growth;
    }

    @Override
    public int currentStats() {
        return currentLevel;
    }

    @Override
    public int currentPrice() {
        return 0;
    }
}
