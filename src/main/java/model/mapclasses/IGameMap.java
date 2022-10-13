package model.mapclasses;

import java.util.List;

public interface IGameMap {
    int getWidth();
    int getHeight();
    Tile[][] getGameMapCoordinates();
    List<Tile> getTiles();
}
