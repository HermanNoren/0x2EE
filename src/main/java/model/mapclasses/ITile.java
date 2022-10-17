package model.mapclasses;

import java.util.List;

/**
 * Interface used by Tile class('s). A Tile is a mathematical node, with branches and neighbors.
 */
public interface ITile {
    /**
     * Method used to get a List of all Edges to the Tile.
     * @return a List with objects of type Edge.
     */
    List<Tile.Edge> getNeighbors();

    /**
     * Method used to get the x-position, not the same as vector position.
     * @return the x-value for tile, not the same as position the position.
     */
    int getX();

    /**
     * Method used to get the y-position, not the same as vector position.
     * @return the y-value for tile, not the same as position the position.
     */
    int getY();

    /**
     * Method used to set tile passable or not passable.
     * @param passable true if passable, false if not.
     */
    void setPassable(boolean passable);

    /**
     * Method used to see if a Tile is passable or not.
     * @return true of false depends on if Tile passable or not.
     */
    boolean isPassable();

    /**
     * Method used to add branch, neighbor to Tile.
     * @param weight cost to travel from two different Tile's.
     * @param neighbor neighboring Tile to Tile.
     */
    void addBranch(int weight, Tile neighbor);

    /**
     * Method used to the set parent Tile.
     * @param parent for Tile.
     */
    void setParent(Tile parent);

    /**
     * Method used to get the parent Tile for Tile.
     * @return parent Tile.
     */
    Tile getParent();

    /**
     * Method used to get distance from Tile to n Tile + the shortest distance from Tile to n Tile.
     * @return f value of Tile
     */
    double getF();

    /**
     * Method used to get the distance from Tile to n Tile.
     * @return g value of Tile
     */
    double getG();

    /**
     * Method used to get the g-value, distance from Tile to n Tile.
     * @param g new g-value
     */
    void setG(double g);

    /**
     * Method used to set new f-value.
     * @param f new f value.
     */
    void setF(double f);

}
