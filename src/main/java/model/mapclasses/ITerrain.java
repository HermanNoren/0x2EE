package model.mapclasses;

import java.util.List;

public interface ITerrain {
    List<Terrain.Edge> getNeighbors();
    int getX();
    int getY();

}
