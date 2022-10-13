package model.gameobjects;

public interface IUpgradable {
    /**
     * Levels up the thing in question.
     */
    void levelUp();

    /**
     * Used to see how the upgradable would look like
     * if it was upgraded.
     * @return The stats of the upgradable.
     */
     int statsIfUpgraded();

    /**
     * The amount of money needed to upgrade the upgradable.
     * @return
     */
     int upgradeCost();

     /**
      * The current state of the upgradable, for example damage in weapon
      * and armor-reduction in armor.
      */
     int currentStat();
}

