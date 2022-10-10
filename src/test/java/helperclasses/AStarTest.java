package helperclasses;
import model.helperclasses.AStar;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AStarTest {
    Terrain[][] terrains;
    @BeforeEach
    void init(){
        GameMap map = new GameMap(100, 100);
        terrains = map.getGameMapCoordinates();
        for (Terrain[] terrainRow : terrains){
            for (Terrain terrain : terrainRow){
                terrain.setPassable(true);
            }
        }
    }
    @Test
    void test_if_search_returns_tile_in_right_direction(){
        assertSame(terrains[10][11], AStar.aStar(terrains[10][10], terrains[10][50]));
    }

    @Test
    void test_if_astar_chooses_shorest_path(){
        for (int i = 5; i < 50; i++)
            terrains[i][12].setPassable(false);
        terrains[5][11].setPassable(false);
        terrains[50][11].setPassable(false);
        assertSame(terrains[9][10], AStar.aStar(terrains[10][10], terrains[10][50]));
    }

    @Test
    void test_if_no_path_returns_null(){
        for (int i = 0; i < 100; i++)
            terrains[i][12].setPassable(false);
        assertNull(AStar.aStar(terrains[10][10], terrains[10][50]));
    }
}
