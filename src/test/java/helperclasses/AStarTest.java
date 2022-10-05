package helperclasses;
import model.helperclasses.AStar;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class AStarTest {
    @Test
    void test_if_search_returns_tile_in_right_direction(){
        GameMap map = new GameMap(100, 100);
        Terrain[][] terrains = map.getGameMapCoordinates();
        for (Terrain[] terrainRow : terrains){
            for (Terrain terrain : terrainRow){
                terrain.setTerrainType(0);
            }
        }
        assertSame(terrains[10][11], AStar.aStar(terrains[10][10], terrains[10][50]));
    }
}
