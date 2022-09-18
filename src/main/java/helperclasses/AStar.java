package helperclasses;

import mapclasses.Terrain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

    public static Terrain aStar(Terrain start, Terrain target){
        PriorityQueue<Terrain> closedList = new PriorityQueue<>();

        PriorityQueue<Terrain> openList = new PriorityQueue<>(); // Contains

        start.setF(start.getG() + start.calculateHeuristic(target));

        openList.add(start);

        while(!openList.isEmpty()){
            Terrain n = openList.peek(); //n = next node

            if(n == target){
                //while (n.getParent() != start)
                //    n = n.getParent();
                return n;
            }

            for(Terrain.Edge edge : n.neighbors){
                Terrain m = edge.node;
                double totalWeight = n.getG() + edge.weight; //

                if(!openList.contains(m) && !closedList.contains(m) && m.isPassable()){
                    m.setParent(n);
                    m.setG(totalWeight);
                    m.setF(m.getG() + m.calculateHeuristic(target));
                    openList.add(m);

                } else {
                    if(totalWeight < m.getG() && m.isPassable()){
                        m.setParent(n);
                        m.setG(totalWeight);
                        m.setF(m.getG() + m.calculateHeuristic(target));

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



    public static void printPath(Terrain target){
        Terrain n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.getParent() != null){
            ids.add(n.getId());
            n = n.getParent();
        }
        ids.add(n.getId());
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        Terrain head = new Terrain(new Vector2(0, 0), true);

        head.setG(0);

        Terrain n1 = new Terrain(new Vector2(16, 16), true);
        Terrain n2 = new Terrain( new Vector2(32, 32), true);
        Terrain n3 = new Terrain(new Vector2(48,48), true);

        head.addBranch(1, n1);
        head.addBranch(1, n2);
        head.addBranch(1, n3);

        n3.addBranch(1, n2);

        Terrain n4 = new Terrain(new Vector2(64,64), true);


        Terrain target = new Terrain(new Vector2(96, 96), true);

        n1.addBranch(1, n4);
        n3.addBranch(1, n4);

        n4.addBranch(1, target);



        Terrain result = aStar(head, target);

        printPath(result);
    }
}
