package model.gameobjects;

public interface IUpgradable {
    void levelUp();
     int currentLevel();
     int currentPrice();

     int statsIfUpgraded();

     int upgradeCost();
}

