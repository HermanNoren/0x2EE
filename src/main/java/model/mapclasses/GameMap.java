package model.mapclasses;

import model.Vector2;

import java.util.*;

/**
 * Class used to create a GameMap. Tile is used in the GameMap.
 * @author Arthur Alexandersson
 */
public class GameMap implements IGameMap{
    private final List<Tile> tiles = new ArrayList<>();
    private final Tile[][] gameMapCoordinates;
    private final int width;
    private final int height;

     /**
     * Game map constructor, set height and width.
     * Width and height is in number of tiles.
     * @param width number of tiles in width
     * @param height number of tiles in height
     */
    public GameMap(int width, int height, boolean obstacles) {
        this.width = width;
        this.height = height;
        this.gameMapCoordinates = new Tile[width][height];

        addCoordinatesAndTiles(width, height);
        if(obstacles) {
            Noise n = new Noise(10, this); // Generates random tile on the game map.
            n.setNoise(gameMapCoordinates, 2);
            createBorder();
        }
        tiles.forEach(this::addNeighbors);

    }

    /**
     * Adds tiles in a width*height matrix and into a list.
     * @param width the width of the game map
     * @param height the height of the game map
     */
    private void addCoordinatesAndTiles(int width, int height) {
        for(int i = 0; i < width; i ++){
            for(int j = 0; j < height; j++){
                gameMapCoordinates[i][j] = new Tile(i, j);
                tiles.add(gameMapCoordinates[i][j]);
            }
        }
    }
    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Tile[][] getGameMapCoordinates() {
        return gameMapCoordinates;
    }

    /**
     * {@inheritDoc}
     * @return defensive copy of tiles
     */
    @Override
    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }

    /**
     * Method used to get a new List of the passable tiles
     * @return List containing all tiles which are passable.
     */
    public List<Tile> getPassableTiles(){
        List<Tile> passableTiles = new ArrayList<>();
        for (Tile tile : tiles){
            if(tile.isPassable()){
                passableTiles.add(tile);
            }
        }
        return passableTiles;
    }


    /**
     * {@inheritDoc}
     */
    private void addNeighbors(Tile current){

        int x = current.getX();
        int y = current.getY();
        // Add left side neighbour
        addNeighbor(x - 1 > -1, x - 1, y, current);
        // Add right side neighbour
        addNeighbor(x + 1 < width, x + 1, y, current);
        // Add top neighbour
        addNeighbor(y - 1 > -1, x, y - 1, current);
        // Add bottom neighbour
        addNeighbor(y + 1 < height, x, y + 1, current);
    }
    private void addNeighbor(boolean x, int x1, int y, Tile current) {
        if (x){
            Tile leftNeighbor = gameMapCoordinates[x1][y];

            if(leftNeighbor != null){
                current.addBranch(1, leftNeighbor);
            }
        }
    }
    /**
     * Method used to create a border on the game map.
     */
    private void createBorder(){
        int col = 0;
        int row = 0;
        while (col < width && row < height){
            if((gameMapCoordinates[col][row].getX())+2 > width ||
                    (gameMapCoordinates[col][row].getY())+2 > height ||
                    (gameMapCoordinates[col][row].getX())-1 < 0 ||
                    (gameMapCoordinates[col][row].getY())-1 < 0){
                gameMapCoordinates[col][row].setPassable(false);
            }
                col ++;
            if(col == width){
                col = 0;
                row++;
            }
        }
    }


}


