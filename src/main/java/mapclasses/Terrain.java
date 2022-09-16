package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.ISprite;

import java.util.List;

public class Terrain implements ISprite, Comparable<Terrain> {

    private final int size = Config.SPRITE_SIZE;
    private Vector2 pos;
    private Rect rect;

    public List<Terrain.Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;

    // Heuristic
    public Vector2 playerPos;

    public Terrain(int x, int y) {
        pos = new Vector2(x, y);
        rect = new Rect(x, y, size, size);
    }

    public static class Edge {
        Edge(int weight, Terrain terrain){
            this.weight = weight;
            this.terrain = terrain;
        }

        public int weight;
        public Terrain terrain;
    }

    public void addBranch(int weight, Terrain terrain){
        Edge newEdge = new Edge(weight, terrain);
        neighbors.add(newEdge);
    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public void update() {

    }

    /**
     *
     * @return
     */
    public int distanceToPlayer(){
        int dx = (int) Math.abs(playerPos.getX() - this.getPos().getX());
        int dy = (int) Math.abs(playerPos.getY() - this.getPos().getY());
        return dy + dx;
    }

    @Override
    public int compareTo(Terrain terrain) {
        if (this.distanceToPlayer() < terrain.distanceToPlayer())
            return -1;
        if (this.distanceToPlayer() > terrain.distanceToPlayer())
            return 1;
        return 0;
    }
}