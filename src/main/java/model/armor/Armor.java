package model.armor;

import model.gameobjects.IUpgradable;

public class Armor implements IArmor, IUpgradable {

    public Armor(){

    }

    public int currentLevel = 1;

    /**
     * @param damageTaken reduces damage
     * @return the damage that the armor absorbs
     */
    @Override
    public double damageReduction(int damageTaken) {
        double reduction = (double) currentLevel /100;
        return damageTaken * reduction;
    }

    public int getLevel(){
        return currentLevel;
    }

    public void levelUpArmor(){
        this.currentLevel++;
    }
    /**
     * Used to increase armorLevel attribute.
     */
    @Override
    public void upgradeArmor() {
        //Upgrades armorLevel
    }

    @Override
    public void levelUp() {
        currentLevel++;
    }

    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }
}
