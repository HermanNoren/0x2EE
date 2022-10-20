package model.upgradables;

/**
 * Used to call the same methods from different objects, see the Upgradable class
 * for more information regarding the usage of this interface.
 * @Author Gustav Gille
 */
public interface IUpgradable {
    /**
     * Levels up the thing in question.
     */
    void upgrade();

    /**
     * Used to see how the Upgradable would look like
     * if it was upgraded.
     * @return The stats of the Upgradable.
     */
     int statsIfUpgraded();

    /**
     * The amount of money needed to upgrade the Upgradable.
     * @return
     */
     int upgradeCost();

     /**
      * The current state of the Upgradable, for example damage in weapon
      * and armor-reduction in armor.
      */
     int currentStats();
}

