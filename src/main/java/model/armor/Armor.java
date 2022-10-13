package model.armor;

import model.gameobjects.IUpgradable;

public class Armor implements IArmor, IUpgradable {

    public Armor(){
    }
    private int currentLevel = 1;
    private int currentArmorPrize = 10;
    private final double armorReductionConstant = 0.08;
    private int growth = 40;

    /**
     * Method to determine how much damage reduction the player can receive,
     * determined by the player's level and an armor constant.
     * @param damageTaken
     * @return
     */
    @Override
    public double damageReduction(int damageTaken) {
        return damageTaken - (currentLevel * armorReductionConstant);
    }
    public int currentDamageReduction() {
        return currentLevel;
    }

    @Override
    public void levelUp() {
        currentLevel++;
        currentArmorPrize =(currentLevel * growth);
    }

    @Override
    public int statsIfUpgraded() {
        return currentLevel + 1;
    }

    @Override
    public int upgradeCost() {
        return currentLevel * growth;
    }
}
