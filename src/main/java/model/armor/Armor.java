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
        double reduction =  currentLevel /100;
        return damageTaken * reduction;
    }
    public double getCurrentDamageReduction() {
        return currentLevel;
    }


    @Override
    public void levelUp() {
        this.currentLevel++;
    }

    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }
}
