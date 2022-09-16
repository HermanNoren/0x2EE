package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    static class Node{
        int value, weight;
        Node(int value, int weight){
            this.value = value;
            this.weight = weight;
        }
    };
    //Adjacency list
    List<List<Node>> adj_list = new ArrayList<>();
    public Graph(List<Edge> edges){

        for (int i = 0; 1 < edges.size(); i++){
            adj_list.add(i, new ArrayList<>());
        }
        for(Edge e: edges){
            adj_list.get(e.src).add(new Node(e.dest, e.weight));
        }

    }
    public static void printGraph(Graph graph){
        int src_node = 0;
        int list_size = graph.adj_list.size();

        while (src_node < list_size){
            for (Node edge : graph.adj_list.get(src_node)){
                System.out.print("Vertex:" + src_node + " ==> " + edge.value +
                        " (" + edge.weight + ")\t");
            }
            System.out.println();
            src_node++;
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(new Edge(0, 1, 2));
        Graph graph =  new Graph(edges);
        Graph.printGraph(graph);
    }
}
