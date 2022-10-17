package model.upgradables;

/**
 * Class which consists of the main parts of an upgradable object.
 */
public abstract class Upgradable implements IUpgradable {
    private int currentlevel;
    private int growth;
    public Upgradable(int currentLevel, int growth){
        this.currentlevel = currentLevel;
        this.growth = growth;
    }

    @Override
    public void upgrade() {
        currentlevel++;
    }

    @Override
    public int statsIfUpgraded() {
        return currentlevel + 1;
    }

    @Override
    public int upgradeCost() {
        return currentlevel * growth;
    }
    public int getCurrentlevel(){
        return currentlevel;
    }

    @Override
    public int currentStats() {
        return currentlevel;
    }

}
