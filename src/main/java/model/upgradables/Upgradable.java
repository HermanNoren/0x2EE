package model.upgradables;

/**
 * An abstract class which contains the blueprint for an Upgradable, uses IUpgradable interfaces
 * for abstraction in the TransactionHandler class.
 * @Author Gustav Gille
 */
public abstract class Upgradable implements IUpgradable {
    private int currentLevel;
    private final int growth;

    /**
     * Constructor declared in classes that extend it must declare. The currentLevel
     * is used to see what level the upgradable is.
     * The growth variable is used to determine the cost of the upgradable.
     * @param currentLevel What level the upgradable starts at.
     * @param growth how fast the upgradable cost will grow.
     */
    public Upgradable(int currentLevel, int growth){
        this.currentLevel = currentLevel;
        this.growth = growth;
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
    public int getCurrentLevel(){
        return currentLevel;
    }

    @Override
    public int currentStats() {
        return currentLevel;
    }

}
