package model.mapclasses;

import java.util.List;

public interface ITile {
    List<Tile.Edge> getNeighbors();
    int getX();
    int getY();
    void setTileType(int type);
    int getTileType();
    void setPassable(boolean passable);
    boolean isPassable();
    void addBranch(int weight, Tile neighbor);
    void setParent(Tile parent);
    Tile getParent();
    double getF();
    double getG();
    void setF(double f);

}
