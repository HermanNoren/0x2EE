package model.helperclasses;

import model.mapclasses.Tile;

import java.util.PriorityQueue;

/**
 * AStar class contains a static method with the AStar algorithm.
 */
public class AStar {

    /**
     * Manhattan heuristic
     * @return
     */
    private static double calculateHeuristic(Tile current, Tile goal){
        double dx = Math.abs(current.getX() - goal.getX());
        double dy = Math.abs(current.getY() - goal.getY());
        double priority = Math.abs(dx-dy)*0.0001;
        return dx + dy + priority;
    }
    /**
     * Static method with A* algorithm, used to find the shortest path between two nodes
     * by using a heuristic to approximate the shortest distance from the start to target to make the algorithm
     * more accurate and efficient. Like the Djikastra algorithm except more efficient since A* uses the
     * heuristic to search through a given path instead of searching through every surrounding node.
     *
     * @param start The start node
     * @param target The target node
     * @return The node which is the target, gets returned once the target has been reached.
     *
     */
    public static Tile aStar(Tile start, Tile target){

        PriorityQueue<Tile> closedList = new PriorityQueue<>();
        PriorityQueue<Tile> openList = new PriorityQueue<>();

        start.setG(0);
        start.setF(calculateHeuristic(start, target));

        openList.add(start);
        while(!openList.isEmpty()){
            Tile n = openList.peek(); //n = next node

            if(n == target){
                while (n.getParent() != start && n != start)
                    n = n.getParent();
                return n;
            }

            for(Tile.Edge edge : n.getNeighbors()){ // Check neighbors of n.
                Tile m = edge.getTile();
                double totalWeight = n.getG() + edge.getWeight();

                if(!openList.contains(m) && !closedList.contains(m) && m.isPassable()){
                    m.setParent(n);
                    m.setG(totalWeight);
                    m.setF(m.getG() + calculateHeuristic(m, target)); // f = g+h
                    openList.add(m);

                }
                else {
                    if(totalWeight < m.getG() && m.isPassable()){
                        m.setParent(n);
                        m.setG(totalWeight);
                        m.setF(m.getG() + calculateHeuristic(m, target));

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);

        }
        return null;
    }
}
