package model.gameobjects.enemies;

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






}
