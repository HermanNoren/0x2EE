package model.mapclasses;

import utility.Noise;

import java.util.*;

/**
 * Game map class
 */
public class GameMap implements IGameMap{
    private final List<Terrain> terrains = new ArrayList<>();
    private final Terrain[][] gameMapCoordinates;
    private final int width;
    private final int height;

    /**
     * Game map constructor, set height and width.
     * Width and height is in number of terrains.
     * @param width number of terrains in width
     * @param height number of terrains in height
     */
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.gameMapCoordinates = new Terrain[width][height];

        addCoordinatesAndTiles(width, height);

        Noise n = new Noise(10, this); // Generates random terrain on the game map.
        n.setNoise(gameMapCoordinates, 1, 2, 2);

        createBorder();
        terrains.forEach(this::addNeighbors);
        n.printTerrainGrid(gameMapCoordinates);
    }

    /**
     * Adds terrains in a width*height matrix and into a list.
     * @param width the width of the game map
     * @param height the height of the game map
     */
    private void addCoordinatesAndTiles(int width, int height) {
        for(int i = 0; i < width; i ++){
            for(int j = 0; j < height; j++){
                gameMapCoordinates[i][j] = new Terrain(i, j);
                terrains.add(gameMapCoordinates[i][j]);
            }
        }
    }

    /**
     * Method used to get the value of the height variable in GameMap
     * @return height of game map
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Method used to get the value of the width variable in GameMap
     * @return width of the game map
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Method used to get the reference to the gameMapCoordinates grid.
     * @return grid of Terrains
     */
    @Override
    public Terrain[][] getGameMapCoordinates() {
        return gameMapCoordinates;
    }

    /**
     * Method used to get a copy of the List of terrains.
     * @return a copy of List<Terrain> terrains.
     */
    @Override
    public List<Terrain> getTerrains() {
        return new ArrayList<>(terrains);
    }

    /**
     * @return List containing all terrains which are passable.
     */
    public List<Terrain> getPassableTerrains(){
        List<Terrain> passableTerrains = new ArrayList<>();
        for (Terrain terrain : terrains){
            if(terrain.isPassable()){
                passableTerrains.add(terrain);
            }
        }
        return passableTerrains;
    }

    /**
     * Add neighbor to the given node. If the presumed neighbor isn't grass then it won't be added as a neighbor.
     */
    private void addNeighbors(Terrain current){
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

    private void addNeighbor(boolean x, int x1, int y, Terrain current) {
        if (x){
            Terrain leftNeighbor = gameMapCoordinates[x1][y];

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
                gameMapCoordinates[col][row].setTerrainType(0);
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


