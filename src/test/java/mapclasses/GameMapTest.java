package mapclasses;

import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GameMapTest {
    private GameMap gameMap;
    @BeforeEach
    void init(){
        gameMap = new GameMap(20, 10);
    }
    @Test
    void test_getHeight_returns_height_of_gameMap(){
        assertEquals(10, gameMap.getHeight());
    }
    @Test
    void test_getWidth_returns_height_of_gameMap(){
        assertEquals(20, gameMap.getWidth());
    }
    @Test
    void test_getGameMapCoordinates_returns_array_of_arrays_with_Terrains(){
        Terrain[][] testCoordinates = new Terrain[20][10];
        for(int i = 0; i < gameMap.getWidth(); i++){
            for(int j = 0; j < gameMap.getHeight(); j++) {
                testCoordinates[i][j] = new Terrain(i, j);
            }
        }
        assertEquals(testCoordinates.getClass(), gameMap.getGameMapCoordinates().getClass());
    }

    @Test
    void test_getTerrains_returns_list_containing_Terrains(){
        List<Terrain> testList = new ArrayList<>();
        assertEquals(testList.getClass(), gameMap.getTerrains().getClass());
    }
}
