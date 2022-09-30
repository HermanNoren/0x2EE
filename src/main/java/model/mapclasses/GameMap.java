package model.mapclasses;

import filehandler.WriteMapToFile;
import model.gameobjects.*;
import model.helperclasses.Vector2;

import java.util.*;


/**
 * Game map class
 */
public class GameMap {
    private final ArrayList<Terrain> terrains = new ArrayList<>();
    private final ArrayList<Entity> entities;
    private final Terrain[][] gameMapCoordinates;
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        gameMapCoordinates = new Terrain[width][height];
        entities = new ArrayList<>();
        addCoordinatesAndTiles(width, height);
        randomizeMap();
        createBorder();

        terrains.forEach(this::addNeighbors);
        WriteMapToFile wr = new WriteMapToFile("maps/map1.txt");
        wr.writeToFile(this);
    }

    public void addEntity(int x, int y, Entity entity){
        gameMapCoordinates[x][y].addEntity(entity);
    }

    private void addCoordinatesAndTiles(int width, int height) {
        for(int i = 0; i < width; i ++){
            for(int j = 0; j < height; j++){
                gameMapCoordinates[i][j] = new Terrain(i, j);
                terrains.add(gameMapCoordinates[i][j]);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Terrain[][] getGameMapCoordinates() {
        return gameMapCoordinates;
    }

    public ArrayList<Terrain> getTerrains() {
        return new ArrayList<>(terrains);
    }

    public void setTerrainType(int x, int y, int type){
        gameMapCoordinates[x][y].setTerrainType(type);
    }

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

    private void createBorder(){
        int col = 0;
        int row = 0;
        while (col < width && row < height){

            if((gameMapCoordinates[col][row].getPos().x/48)+2 > width ||
                    (gameMapCoordinates[col][row].getPos().y/48)+2 > height ||
                    (gameMapCoordinates[col][row].getPos().x/48)-1 < 0 ||
                    (gameMapCoordinates[col][row].getPos().y/48)-1 < 0){

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


    private void randomizeMap(){
        Random random = new Random();
        for(int i = 0; i< width; i ++){
            for (int j = 0; j< height; j++){

            }
        }
    }
    private void generateWall(){
        Terrain terrain = chooseRandomTerrain();
        Vector2 terrainPos = terrain.getPos();
        int x = (int) terrainPos.x/48;
        int y = (int) terrainPos.y/48;

    }
    private Terrain chooseRandomTerrain(){
        Random random = new Random();
        int randowCol = random.nextInt(width-1);
        int randomRow = random.nextInt(height-1);
        return gameMapCoordinates[randowCol][randomRow];
    }
}


