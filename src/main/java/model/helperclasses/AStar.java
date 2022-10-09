package model.helperclasses;

import model.mapclasses.Terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * AStar class contains a static method with the AStar algorithm.
 */
public class AStar {
    /**
     * Manhattan heuristic
     * @return double value.
     */
    private static double calculateHeuristic(Terrain current, Terrain goal){
        double dx = Math.abs(current.getX() - goal.getX());
        double dy = Math.abs(current.getY() - goal.getY());
        return (dx + dy);
    }
    /**
     * Static method with A* algorithm, used to find the shortest path between two nodes
     * by using a heuristic to approximate the shortest distance from the start to target to make the algorithm
     * more accurate and efficient. Like the Djikastra algorithm except more efficient since A* uses the
     * heuristic to start with a given path instead of searching through every surrounding node.
     *
     * @param start The start node
     * @param target The target node
     * @return The node which is the target, gets returned once the target has been reached.
     *
     */
    public static Terrain aStar(Terrain start, Terrain target){

        PriorityQueue<Terrain> closedList = new PriorityQueue<>();
        PriorityQueue<Terrain> openList = new PriorityQueue<>();

        start.setG(0);
        start.setF(calculateHeuristic(start, target));

        openList.add(start);
        while(!openList.isEmpty()){
            Terrain n = openList.peek(); //n = next node

            if(n == target){
                while (n.getParent() != start && n != start)
                    n = n.getParent();
                return n;
            }

            for(Terrain.Edge edge : n.getNeighbors()){ // Check neighbors of n.
                Terrain m = edge.terrain;
                double totalWeight = n.getG() + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m) && m.isPassable()){
                    m.setParent(n);
                    m.setG(totalWeight);
                    m.setF(m.getG() + calculateHeuristic(m, target)); // f = g+h
                    openList.add(m);
                }

                // TODO: Remove if unnecessary

                else {
                    if(totalWeight < m.getG() && m.isPassable()){
                        m.setParent(n);
                        m.setG(totalWeight);
                        m.setF(m.getG() + calculateHeuristic(m, target));

//                        if(closedList.contains(m)){
//                            closedList.remove(m);
//                            openList.add(m);
//                        }
                    }
                }

            }

            openList.remove(n);
            closedList.add(n);

        }
        return null;
    }
}
