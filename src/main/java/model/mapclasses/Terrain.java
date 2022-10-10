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
public class Terrain implements IGameObject, Comparable<Terrain> {
    private final int size = Config.TERRAIN_SIZE;
    private final Vector2 pos;
    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; // g(n), n = next node, distance from start to n.
    private final List<Edge> neighbors;
    private Terrain parent = null;
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
        this.terrainType = 0;
        this.passable = true;
        this.neighbors = new ArrayList<>();
        this.pos = new Vector2(x, y);
        this.pos.setX(x*size);
        this.pos.setY(y*size);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }
    public void setF(double f) {
        this.f = f;
    }
    public double getG() {
        return g;
    }
    public void setG(double g) {
        this.g = g;
    }
    public Terrain getParent() {
        return parent;
    }
    public void setParent(Terrain parent) {
        this.parent = parent;
    }
    @Override
    public int compareTo(Terrain n) {
        return Double.compare(this.f, n.f);
    }

    /**
     *
     * @param type : the type indicates whether the tile is passable or not. Tyles 1, 2 and 3 are not passable.
     */
    public void setTerrainType(int type) {
        if(type == 1 || type == 2 || type == 3){
            setPassable(false);
        }
        else{
            setPassable(true);
        }
        this.terrainType = type;
    }

    public int getTerrainType() {
        return terrainType;
    }

    public void setPassable(boolean passable){
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    /**
     * @param weight
     * @param neighbor
     */
    public void addBranch(int weight, Terrain neighbor){
        Terrain.Edge newEdge = new Terrain.Edge(weight, neighbor);
        neighbors.add(newEdge);
    }

    public double getF() {
        return f;
    }

    public static class Edge {
        private int weight;
        private Terrain terrain;
        Edge(int weight, Terrain terrain){
            this.weight = weight;
            this.terrain = terrain;
        }

        public Terrain getTerrain() {
            return terrain;
        }

        public int getWeight() {
            return weight;
        }
    }

}
