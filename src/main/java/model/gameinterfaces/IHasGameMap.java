package model.gameinterfaces;

import model.mapclasses.GameMap;

public interface IHasGameMap {
    GameMap getGameMap();
    int getMapSize();
}
