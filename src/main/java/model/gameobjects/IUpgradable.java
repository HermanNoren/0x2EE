package model.gameobjects;

public interface IUpgradable {
    void levelUp();
     int statsIfUpgraded();
     int upgradeCost();

     int currentStat();
}

