package model.gameinterfaces;

import model.gameobjects.Player;

public interface IHasPlayer {
    Player getPlayer();
    Boolean isPlayerDead();
    boolean isTopFive();

}
