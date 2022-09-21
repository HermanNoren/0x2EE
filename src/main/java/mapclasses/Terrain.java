package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import main.Game;
import gameobjects.IGameObject;
import gameobjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts a Node class, every Terrain is a node in the map.
 */
public class Terrain implements IGameObject, Comparable<Terrain> {
    private final int size = Config.SPRITE_SIZE;
    private Vector2 pos;
    private Rect rect;
    private boolean passable;
    private Game game = Game.getInstance();
    private final Player player;
    private static int idCounter = 0;
    private int id; // Id of the node to keep track of the path later on. (The "name" of the node)
    private double f = 0; // Will later be equal to g + h
    private double g = 0; // g(n), n = next node, distance from start to n.
    public List<Terrain.Edge> neighbors;
    private Terrain parent = null;
    private Vector2 playerPos; // Target node

    public Terrain(Vector2 vector2, boolean passable){
        this.player = game.getPlayer();
        playerPos = player.getPos();
        this.neighbors = new ArrayList<>();
        this.id = idCounter++;
        this.pos = vector2;
        this.rect = new Rect((int) Math.round(vector2.getX()), (int) Math.round(vector2.getY()), size, size);
        this.passable = passable;
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
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public Rect getRect() {
        return new Rect(rect);
    }

    @Override
    public void update() {
        playerPos = player.getPos();
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

    public int getId() {
        return id;
    }

    public boolean isPassable(){
        return passable;
    }


    @Override
    public int compareTo(Terrain n) {
        return Double.compare(this.f, n.getF());
    }

    public static class Edge {

        public int weight;
        public Terrain node;

        Edge(int weight, Terrain node){
            this.weight = weight;
            this.node = node;
        }

    }
    public void addBranch(int weight, Terrain node){
        Terrain.Edge newEdge = new Terrain.Edge(weight, node);
        neighbors.add(newEdge);
    }

    /**
     * Manhattan heuristic
     * @return
     */
    public double calculateHeuristic(Terrain node){
        int D = 1;
        double dx = Math.abs(node.getPos().getX() - playerPos.getX());
        double dy = Math.abs(node.getPos().getY() - playerPos.getY());
        return D*(dx + dy);
    }
}
