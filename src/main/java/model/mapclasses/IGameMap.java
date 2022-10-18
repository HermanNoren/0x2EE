package model.mapclasses;

import java.util.List;

public interface IGameMap {
    /**
     * Method used to get the number of Tiles in width
     * @return width of GameMap
     */
    int getWidth();

    /**
     * Method used to get the number of Tiles in height
     * @return height of GameMap
     */
    int getHeight();

    /**
     * Method used to get the grid containing all Tiles in the GameMap.
     * @return Grid with type Tile
     */
    Tile[][] getGameMapCoordinates();

    /**
     * Method used to get a List of all the Tiles
     * @return List with types Tile
     */
    List<Tile> getTiles();
}
