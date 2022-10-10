package model.mapclasses;

import config.Config;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;


import java.util.ArrayList;
import java.util.List;

/**
 * Terrain is the node representation for the gamemap
 */
public class Terrain implements IGameObject, ITerrain, Comparable<Terrain> {
    private final int size = Config.TERRAIN_SIZE;
    private final Vector2 pos;
    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; // g(n), n = next node, distance from start to n.
    private final List<Edge> neighbors;
    private Terrain parent;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private int terrainType;
    private boolean passable;

    public Terrain(int x, int y){
        this.x = x;
        this.y = y;

        this.height = size;
        this.width = size;
        this.parent = null;
        this.terrainType = 0;
        this.passable = true;
        this.neighbors = new ArrayList<>();
        this.pos = new Vector2(x, y);
        this.pos.setX(x*size);
        this.pos.setY(y*size);
    }

    /**
     * Method used to get the height of the terrain
     * @return Height of terrain
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Used to get the width of terrain.
     * @return Width of terrain
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Method used to get the neighbors of terrain.
     * List containing Edge, every edge has a terrain.
     * @return List with Edges
     */
    @Override
    public List<Terrain.Edge> getNeighbors() {
        return neighbors;
    }

    /**
     * @return X-position
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * @return Y-position
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Used to get the position of terrain.
     * @return new Vector2
     */
    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * @return center of the terrain
     */
    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    /**
     * Sets the f-value for the terrain, used in A*
     * @param f new f-value
     */
    public void setF(double f) {
        this.f = f;
    }

    /**
     * Used in A*
     * @return f-value
     */
    public double getF(){
        return this.f;
    }

    /**
     * Returns g-value, used in A*.
     * @return g-value
     */
    public double getG() {
        return g;
    }

    /**
     * Sets g-value of the terrain, used in A* algorithm
     * @param g value
     */
    public void setG(double g) {
        this.g = g;
    }

    /**
     * @return the parent of the terrain. Null if none.
     */
    public Terrain getParent() {
        return parent;
    }

    /**
     * Sets the parent terrain, used to remember previous terrain in search algorithm.
     * @param parent parent terrain
     */
    public void setParent(Terrain parent) {
        this.parent = parent;
    }

    /**
     * @param n the object to be compared.
     * @return -1 if this.g is less than parameter g value.
     */
    @Override
    public int compareTo(Terrain n) {
        return Double.compare(this.g, n.getG());
    }

    /**
     *
     * @param type : the type indicates whether the tile is passable or not. Tyles 1, 2 and 3 are not passable.
     */
    public void setTerrainType(int type) {
        if(type == 1 || type == 2 || type == 3){
            setPassable(false);
        }
        this.terrainType = type;
    }

    /**
     * Method used to get the type of terrain
     * @return type of terrain, returns an int.
     */
    public int getTerrainType() {
        return terrainType;
    }

    /**
     * Method used to set the boolean passable. If false, should act as an obstacle.
     * @param passable boolean to set.
     */
    public void setPassable(boolean passable){
        this.passable = passable;
    }

    /**
     * Method used to get the
     * @return if terrain is passable.
     */
    public boolean isPassable() {
        return passable;
    }
    /**
     * Method used to add branch and neighbor to terrain.
     * @param weight weight of the branch.
     * @param neighbor neighbor terrain.
     */
    public void addBranch(int weight, Terrain neighbor){
        Terrain.Edge newEdge = new Terrain.Edge(weight, neighbor);
        neighbors.add(newEdge);
    }

    /**
     *
     */
    public static class Edge {
        public int weight;
        public Terrain terrain;
        Edge(int weight, Terrain terrain){
            this.weight = weight;
            this.terrain = terrain;
        }
    }
}