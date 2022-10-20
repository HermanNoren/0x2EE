package model.gameinterfaces;

import model.mapclasses.GameMap;

/**
 * Interface for fetching information about a game map
 */
public interface IHasGameMap {
    GameMap getGameMap();
    int getMapSize();
}
