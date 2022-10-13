package model.mapclasses;

import config.Config;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;


import java.util.ArrayList;
import java.util.List;

/**
 * Tile is the node representation for the gamemap
 */
public class Tile implements IGameObject, ITile, Comparable<Tile> {
    private final int size = Config.TILE_SIZE;
    private final Vector2 pos;
    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; // g(n), n = next node, distance from start to n.
    private final List<Edge> neighbors;
    private Tile parent = null;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private int tileType;
    private boolean passable;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
        this.height = size;
        this.width = size;
        this.tileType = 0;
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
    @Override
    public double getF() {
        return f;
    }
    @Override
    public Tile getParent() {
        return parent;
    }
    @Override public void setParent(Tile parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Tile n) {
        return Double.compare(this.f, n.f);
    }

    /**
     *
     * @param type the type indicates whether the tile is passable or not.
     */
    public void setTileType(int type) {
        this.tileType = type;
    }

    public int getTileType() {
        return tileType;
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
    public void addBranch(int weight, Tile neighbor){
        Tile.Edge newEdge = new Tile.Edge(weight, neighbor);
        neighbors.add(newEdge);
    }

    public static class Edge {
        private int weight;
        private Tile tile;
        Edge(int weight, Tile tile){
            this.weight = weight;
            this.tile = tile;
        }

        public Tile getTile() {
            return tile;
        }

        public int getWeight() {
            return weight;
        }
    }

}
