package model.mapclasses;

import config.Config;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;


import java.util.ArrayList;
import java.util.List;

/**
 * Tile is a mathematical representation of Node.
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
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
    public List<Edge> getNeighbors() {
        return neighbors;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int getY() {
        return y;
    }
    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    /**
     * {@inheritDoc}
     * @param f {@inheritDoc}
     */
    @Override
    public void setF(double f) {
        this.f = f;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double getG() {
        return g;
    }

    /**
     * {@inheritDoc}
     * @param g {@inheritDoc}
     */
    @Override
    public void setG(double g) {
        this.g = g;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double getF() {
        return f;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public Tile getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     * @param parent {@inheritDoc}
     */
    @Override
    public void setParent(Tile parent) {
        this.parent = parent;
    }

    /**
     * {@inheritDoc}
     * @param n {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public int compareTo(Tile n) {
        return Double.compare(this.f, n.f);
    }


    /**
     * {@inheritDoc}
     * @param passable {@inheritDoc}
     */
    @Override
    public void setPassable(boolean passable){
        this.passable = passable;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public boolean isPassable() {
        return passable;
    }

    /**
     * {@inheritDoc}
     * @param weight {@inheritDoc}
     * @param neighbor {@inheritDoc}
     */
    @Override
    public void addBranch(int weight, Tile neighbor){
        Tile.Edge newEdge = new Tile.Edge(weight, neighbor);
        neighbors.add(newEdge);
    }

    /**
     * Static class, used to add neighbor, and Edge from on Tile to another.
     */
    public static class Edge {
        private int weight;
        private Tile tile;
        protected Edge(int weight, Tile tile){
            this.weight = weight;
            this.tile = tile;
        }

        /**
         * Method used to get neighbor Tile.
         * @return Tile, the neighbor.
         */
        public Tile getTile() {
            return tile;
        }

        /**
         * Method used to get the weight of the branch.
         * @return weight of the branch.
         */
        public int getWeight() {
            return weight;
        }
    }

}
