package model.mapclasses;

import config.Config;
import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Rect;
import model.helperclasses.Vector2;


import java.util.ArrayList;
import java.util.List;


/**
 * Acts a Node class, every Terrain is a node in the map.
 */
public class Terrain implements IGameObject, Comparable<Terrain> {
    private final int size = Config.SPRITE_SIZE*3;

    private Game game = Game.getInstance();
    private List<Entity> entities = new ArrayList<>();
    private Vector2 pos;
    private Rect rect;
    private static int idCounter = 0;
    private int id;

    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; // g(n), n = next node, distance from start to n.
    private ArrayList<Terrain.Edge> neighbors;
    private Terrain parent = null;
    private int x;
    private int y;
    private int terrainType;
    private boolean passable;

    public Terrain(int x, int y){
        this.x = x;
        this.y = y;
        this.terrainType = 0;
        this.passable = true;
        this.neighbors = new ArrayList<>();
        this.pos = new Vector2(x, y);
        this.pos.x *=48;
        this.pos.y *=48;
        this.id = idCounter++;
        this.rect = new Rect(x,y, size, size);
    }

    public int getWidth(){
        return rect.getWidth();
    }

    public ArrayList<Edge> getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return rect.getHeight();
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
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

    public void setTerrainType(int type) {
        if(type == 1){
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

    public static class Edge {

        public int weight;
        public Terrain terrain;
        Edge(int weight, Terrain terrain){
            this.weight = weight;
            this.terrain = terrain;
        }


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
    public void update() {
    }
}