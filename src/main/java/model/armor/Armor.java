package model.armor;

import model.gameobjects.IUpgradable;

public class Armor implements IArmor, IUpgradable {
    private final double armorReductionConstant;
    private final int growth;

    public Armor(){
        armorReductionConstant = 0.08;
        growth = 40;
    }
    private int currentLevel = 1;


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
    public void levelUp() {
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
    public int currentStat() {
        return currentLevel;
    }
}
