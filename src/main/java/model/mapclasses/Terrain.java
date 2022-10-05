package model.mapclasses;

import config.Config;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;


import java.util.ArrayList;

/**
 * Acts a Node class, every Terrain is a node in the map.
 */
public class Terrain implements IGameObject, Comparable<Terrain> {
    private final int size = Config.SPRITE_SIZE*3;
    private final ArrayList<Entity> entities;
    private Vector2 pos;
    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; // g(n), n = next node, distance from start to n.
    private ArrayList<Terrain.Edge> neighbors;
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
        this.entities = new ArrayList<>();
        this.pos = new Vector2(x, y);
        this.pos.setX(x*48);
        this.pos.setY(y*48);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public ArrayList<Edge> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Vector2 getCenter() {
        return null;
    }

    public double getF() {
        return f;
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

    public int getTerrainType() {
        return terrainType;
    }

    public void setPassable(boolean passable){
        this.passable = passable;
    }

    public boolean isPassable() {
        return passable;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * @param weight
     * @param neighbor
     */
    public void addBranch(int weight, Terrain neighbor){
        Terrain.Edge newEdge = new Terrain.Edge(weight, neighbor);
        neighbors.add(newEdge);
    }

    /**
     * Manhattan heuristic
     * @return
     */
    public double calculateHeuristic(Terrain current, Terrain goal){
        int D = 1;
        double dx = Math.abs(current.x - goal.x);
        double dy = Math.abs(current.y - goal.y);
        return D*(dx + dy);
    }

    @Override
    public void update(double dt) {
    }

    public static class Edge {
        public int weight;
        public Terrain terrain;
        Edge(int weight, Terrain terrain){
            this.weight = weight;
            this.terrain = terrain;
        }
    }

}
