package sprites.enemies;

import main.Game;
import sprites.Player;

public class EnemyMovement {
    //TODO implement movement for enemy
    //TODO implement data structure (Graph) to store tiles(Vertices) and paths to other tiles (Edges).


    //TODO Dijkstra's algorithm https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm??
    //TODO A* algorithm https://en.wikipedia.org/wiki/A%2a_search_algorithm??
        // Very smart algorithm which will find the shortest path to the specified target. It uses a "brain"
        // (Approximation) too find the shortest path.
        // Every step, choose the tile with the lowest 'f' value, f is calculated as the sum of 'g' and 'h'
        // g = cost to move from starting tile(enemy starting position) to a given tile(any tile),
        // following the path generated to get there.
        // h = an estimated cost of moving from the given() tile to the destination tile.
        // Heuristic (Manhattan/Euclidean/Diagonal)

        // Heuristics:
        // - Manhattan: h = abs (current_cell.x – goal.x) + abs (current_cell.y – goal.y);
            // When to use this heuristic? –
            // When we are allowed to move only in four directions only (right, left, top, bottom)


        // - Diagonal:
            // dx = abs(current_cell.x – goal.x)
            // dy = abs(current_cell.y – goal.y)
            // h = D * (dx + dy) + (D2 - 2 * D) * min(dx, dy), D = distance across 1 tile.
            // When to use? When we are allowed to move in 8 directions, similar to the queen in chess.
        // - Euclidean (Euclidean distance formula)




    //Algorithm:
    /*
    // A* Search Algorithm
1.  Initialize the open list
2.  Initialize the closed list
    put the starting node on the open
    list (you can leave its f at zero)

3.  while the open list is not empty
    a) find the node with the least f on
       the open list, call it "q"

    b) pop q off the open list

    c) generate q's 8 successors and set their
       parents to q

    d) for each successor
        i) if successor is the goal, stop search

        ii) else, compute both g and h for successor
          successor.g = q.g + distance between
                              successor and q
          successor.h = distance from goal to
          successor (This can be done using many
          ways, we will discuss three heuristics-
          Manhattan, Diagonal and Euclidean
          Heuristics)

          successor.f = successor.g + successor.h

        iii) if a node with the same position as
            successor is in the OPEN list which has a
           lower f than successor, skip this successor

        iV) if a node with the same position as
            successor  is in the CLOSED list which has
            a lower f than successor, skip this successor
            otherwise, add  the node to the open list
     end (for loop)

    e) push q on the closed list
    end (while loop)
     */

}
