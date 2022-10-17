package model.mapclasses;

import java.util.List;

/**
 * Interface used by Tile class('s). A Tile is a mathematical node, with branches and neighbors.
 */
public interface ITile {
    /**
     * @return a List with objects of type Edge, represents the Tile's neighbors.
     */
    List<Tile.Edge> getNeighbors();

    /**
     * @return the x-value for tile, not the same as position the position.
     */
    int getX();

    /**
     * @return the y-value for tile, not the same as position the position.
     */
    int getY();
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
