package model.gameinterfaces;

import model.gameobjects.Player;

public interface IPlayerGettable {
    Player getPlayer();
    Boolean isPlayerDead();
    boolean isTopFive();

}
