package model.mapclasses;

import java.util.List;

public interface IGameMap {
    int getWidth();
    int getHeight();
    Terrain[][] getGameMapCoordinates();
    List<Terrain> getTerrains();
}
