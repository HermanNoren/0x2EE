package helperclasses;
import model.helperclasses.AStar;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AStarTest {
    Tile[][] tiles;
    @BeforeEach
    void init(){
        GameMap map = new GameMap(100, 100);
        tiles = map.getGameMapCoordinates();
        for (Tile[] tileRow : tiles){
            for (Tile tile : tileRow){
                tile.setPassable(true);
            }
        }
    }
    @Test
    void test_if_search_returns_tile_in_right_direction(){
        assertSame(tiles[10][11], AStar.aStar(tiles[10][10], tiles[10][50]));
    }

    @Test
    void test_if_astar_chooses_shorest_path(){
        for (int i = 5; i < 50; i++)
            tiles[i][12].setPassable(false);
        tiles[5][11].setPassable(false);
        tiles[50][11].setPassable(false);
        assertSame(tiles[9][10], AStar.aStar(tiles[10][10], tiles[10][50]));
    }

    @Test
    void test_if_no_path_returns_null(){
        for (int i = 0; i < 100; i++)
            tiles[i][12].setPassable(false);
        assertNull(AStar.aStar(tiles[10][10], tiles[10][50]));
    }
}
