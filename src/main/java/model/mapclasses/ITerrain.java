package model.mapclasses;

import java.util.List;

public interface ITerrain {
    List<Terrain.Edge> getNeighbors();
    int getX();
    int getY();
    void setTerrainType(int type);
    int getTerrainType();
    void setPassable(boolean passable);
    boolean isPassable();
    void addBranch(int weight, Terrain neighbor);
    void setParent(Terrain parent);
    Terrain getParent();

}
