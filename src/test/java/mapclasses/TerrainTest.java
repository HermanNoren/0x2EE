package mapclasses;

import config.Config;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class TerrainTest {

    Terrain terrain;
    @BeforeEach
    void init(){
        terrain = new Terrain(10, 10);
    }

    @Test
    void test_getHeight_returns_height_of_Terrain(){
        assertEquals(Config.TERRAIN_SIZE, terrain.getHeight());
    }

    @Test
    void test_getWidth_returns_height_of_Terrain(){
        assertEquals(Config.TERRAIN_SIZE, terrain.getWidth());
    }

    @Test
    void test_getX_returns_x_position_of_terrain(){
        assertEquals(10, terrain.getX());
    }

    @Test
    void test_getY_returns_y_position_of_terrain(){
        assertEquals(10, terrain.getY());
    }

    @Test
    void test_getG_returns_g_value_of_Terrain(){
        assertEquals(Double.MAX_VALUE, terrain.getG());
    }
    @Test
    void test_setG_sets_g_value_of_Terrain(){
        terrain.setG(10);
        assertEquals(10, terrain.getG());
    }

    @Test
    void test_getF_returns_f_value_of_terrain(){
        assertEquals(Double.MAX_VALUE, terrain.getF());
    }
    @Test
    void test_setF_sets_f_value_of_terrain(){
        terrain.setF(10);
        assertEquals(10, terrain.getF());
    }

    @Test
    void test_getParent_returns_null_on_new_Terrain(){
        assertNull(terrain.getParent());
    }
    @Test
    void test_setParent_sets_parent_Terrain_to_Terrain(){
        Terrain testTerrain = new Terrain(11, 11);
        terrain.setParent(testTerrain);
        assertEquals(testTerrain, terrain.getParent());
    }

    @Test
    void test_getTerrainType_returns_0_for_new_Terrain(){
        assertEquals(0, terrain.getTerrainType());
    }

    @Test
    void test_setTerrainType_sets_TerrainType_of_Terrain(){
        terrain.setTerrainType(1);
        assertEquals(1, terrain.getTerrainType());
    }

    @Test
    void test_isPassable_returns_true_for_new_Terrain(){
        assertTrue(terrain.isPassable());
    }

    @Test
    void test_setPassable_sets_boolean_to_false_on_Terrain(){
        terrain.setPassable(false);
        assertFalse(terrain.isPassable());
    }
    @Test
    void test_setPassable_sets_boolean_to_true_on_Terrain() {
        terrain.setPassable(false);
        terrain.setPassable(true);
        assertTrue(terrain.isPassable());
    }

    @Test
    void test_getNeighbors_returns_empty_list_on_new_Terrain(){
        assertTrue(terrain.getNeighbors().isEmpty());
    }

    @Test
    void test_getNeighbors_returns_List_of_Edges(){
        List<Terrain.Edge> testList = new ArrayList<>();
        assertEquals(testList.getClass(), terrain.getNeighbors().getClass());
    }

    @Test
    void test_addBranch_adds_Edge_with_neighbor_to_Terrain(){
        Terrain testTerrain = new Terrain(11, 11);
        terrain.addBranch(1, testTerrain);

        assertEquals(testTerrain, terrain.getNeighbors().get(0).terrain);
    }
    @Test
    void test_compareTo_compares_two_terrains_f_values_1_if_larger(){
        Terrain testTerrain = new Terrain(1,1);
        testTerrain.setF(1);
        terrain.setF(2);
        assertEquals(1, terrain.compareTo(testTerrain));
    }

    @Test
    void test_compareTo_compares_two_terrains_f_values_negative_1_if_smaller(){
        Terrain testTerrain = new Terrain(1,1);
        testTerrain.setF(3);
        terrain.setF(2);
        assertEquals(-1, terrain.compareTo(testTerrain));
    }

    @Test
    void test_getPos_returns_new_Vector2(){
        Vector2 testVector = new Vector2(1, 1);
        assertEquals(testVector.getClass(), terrain.getPos().getClass());
    }
    @Test
    void test_getPos_returns_new_Vector2_with_same_x_value(){
        Vector2 testVector = new Vector2(480, 480);
        assertEquals(testVector.getX(), terrain.getPos().getX());
    }
    @Test
    void test_getPos_returns_new_Vector2_with_same_y_value(){
        Vector2 testVector = new Vector2(480, 480);
        assertEquals(testVector.getY(), terrain.getPos().getY());
    }

    @Test
    void test_getCenter_returns_center_vector_xpos_of_Terrain(){
        Vector2 testVector = new Vector2(48+24, 48+24);
        Terrain testTerrain = new Terrain(1, 1);
        assertEquals(testVector.getX(), testTerrain.getCenter().getX());
    }

    @Test
    void test_getCenter_returns_new_center_vector_ypos_of_Terrain(){
        Vector2 testVector = new Vector2(48+24, 48+24);
        Terrain testTerrain = new Terrain(1, 1);
        assertEquals(testVector.getY(), testTerrain.getCenter().getY());
    }

}
