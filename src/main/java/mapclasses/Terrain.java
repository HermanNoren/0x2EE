package mapclasses;

import config.Config;
import helperclasses.Rect;
import helperclasses.Vector2;
import main.Game;
import sprites.ISprite;
import sprites.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts a Node class, every Terrain is a node in the map.
 */
public class Terrain implements ISprite, Comparable<Terrain> {

    private final int size = Config.SPRITE_SIZE;
    private Vector2 pos;
    private Rect rect;
    private Game game = Game.getInstance();
    private Player player;
    private static int idCounter = 0;
    private int id; // Id of the node to keep track of the path later on. (The "name" of the node)

    private double f = Double.MAX_VALUE; // Will later be equal to g + h
    private double g = Double.MAX_VALUE; //

    private Terrain parent = null;

    public Terrain(int x, int y) {
//        this.player = game.getPlayer();

//        this.pos = new Vector2(x, y);
//        this.rect = new Rect(x, y, size, size);
    }
    public Terrain(Vector2 vector2){
        this.player = new Player(100, 100, 0.5, 1);
        playerPos = player.getPos();
        this.neighbors = new ArrayList<>();
        this.id = idCounter++;
        System.out.println(id);
        this.pos = vector2;
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

    private Vector2 playerPos; //Goal node

    @Override
    public void update() {

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

    public void setId(int id) {
        this.id = id;
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
    public List<Terrain.Edge> neighbors;
    public void addBranch(int weight, Terrain node){
        Terrain.Edge newEdge = new Terrain.Edge(weight, node);
        neighbors.add(newEdge);
    }

    /**
     * Manhattan heuristic
     * @param target
     * @return
     */
    public double calculateHeuristic(Terrain target){
        int D = 1;
        double dx = Math.abs(getPos().getX() - playerPos.getX());
        double dy = Math.abs(getPos().getY() - playerPos.getY());
        return D * (dx + dy);
    }
}
