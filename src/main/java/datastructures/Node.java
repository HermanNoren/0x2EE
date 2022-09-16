package datastructures;

import helperclasses.Vector2;
import mapclasses.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    // Id for readability of result purposes
    private static int idCounter = 0;
    public int id;
    private Terrain terrain;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    // Hardcoded heuristic
    public Vector2 playerPos;

    Node(Vector2 playerPos, Terrain terrain){
        this.playerPos = playerPos;
        this.terrain = terrain;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {
        Edge(int weight, Node node){
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target){
        double dx = terrain.getPos().getX() - playerPos.getX();
        double dy = terrain.getPos().getY() - playerPos.getY();
        return dx + dy;
    }
}