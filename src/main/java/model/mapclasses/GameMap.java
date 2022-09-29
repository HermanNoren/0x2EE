package model.mapclasses;

import utility.Noise;
import model.gameobjects.*;
import java.util.*;

/**
 * Game map class
 */
public class GameMap {
    private final List<Terrain> terrains = new ArrayList<>();
    private final List<Entity> entities;
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
        gameMapCoordinates = new Terrain[width][height];
        entities = new ArrayList<>();
        addCoordinatesAndTiles(width, height);

//        Noise n = new Noise(1, this); // Generates random terrain on the game map.
//
//        n.init();
//        n.setTerrainTypes(gameMapCoordinates);
        createBorder();

        terrains.forEach(this::addNeighbors);
//        n.printTerrainGrid(gameMapCoordinates);
    }

    /**
     * Method which can be used to add entities to the game map.
     * @param x x-position of entity
     * @param y y-position of entity
     * @param entity object of type Entity.
     */
    public void addEntity(int x, int y, Entity entity){
        gameMapCoordinates[x][y].addEntity(entity);
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
    public int getHeight() {
        return height;
    }

    /**
     * Method used to get the value of the width variable in GameMap
     * @return width of the game map
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method used to get the reference to the gameMapCoordinates grid.
     * @return grid of Terrains
     */
    public Terrain[][] getGameMapCoordinates() {
        return gameMapCoordinates;
    }

    /**
     * Method used to get a copy of the List of terrains.
     * @return a copy of List<Terrain> terrains.
     */
    public List<IGameObject> getTerrains() {
        return new ArrayList<>(terrains);
    }

    /**
     * Method used to set specific terrain's type.
     * @param x x-position in the coordinate grid.
     * @param y y-position in the coordinate grid.
     * @param type type of the terrain, type = 0, 1, 2 or 3.
     */
    public void setTerrainType(int x, int y, int type){
        gameMapCoordinates[x][y].setTerrainType(type);
    }

    /**
     * Method used to set specific tile to be passable or not.
     * @param x x-position in the coordinate grid.
     * @param y y-position in the coordinate grid.
     * @param passable boolean true if passable, false if not passable.
     */
    public void setTerrainPassable(int x, int y, boolean passable){
        gameMapCoordinates[x][y].setPassable(passable);
    }

    /**
     * Add neighbor to the given node. If the presumed neighbor isn't grass then it won't be added as a neighbor.
     */
    public void addNeighbors(Terrain current){
        int x = current.getX();
        int y = current.getY();

        // Add left side neighbour
        if (current.isPassable() && (x-1) > -1){
            Terrain leftNeighbor = gameMapCoordinates[x-1][y];

            if(leftNeighbor != null){
                if(leftNeighbor.isPassable()){
                    current.addBranch(1, leftNeighbor);
                }
            }
        }

        // Add right side neighbour
        if(current.isPassable() && (x+1) < width){
            Terrain rightNeighbor = gameMapCoordinates[x+1][y];
            if(rightNeighbor != null){
                if(rightNeighbor.isPassable()) {
                    current.addBranch(1, rightNeighbor);
                }
            }
        }

        // Add top neighbour
        if (current.isPassable() && (y-1) > -1){
            Terrain topNeighbor = gameMapCoordinates[x][y-1];
            if(topNeighbor != null){
                if(topNeighbor.isPassable()){
                    current.addBranch(1, topNeighbor);
                }
            }
        }

        // Add bottom neighbour
        if(current.isPassable() && (y+1) < height){
            Terrain bottomNeighbor = gameMapCoordinates[x][y+1];
            if(bottomNeighbor != null){
                if (bottomNeighbor.isPassable()){
                    current.addBranch(1, bottomNeighbor);
                }
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

            if((gameMapCoordinates[col][row].getPos().x)+2 > width ||
                    (gameMapCoordinates[col][row].getPos().y)+2 > height ||
                    (gameMapCoordinates[col][row].getPos().x)-1 < 0 ||
                    (gameMapCoordinates[col][row].getPos().y)-1 < 0){

                gameMapCoordinates[col][row].setTerrainType(1);
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


