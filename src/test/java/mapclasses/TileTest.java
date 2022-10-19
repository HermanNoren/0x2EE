package mapclasses;

import config.Config;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private Tile tile;
    @BeforeEach
    void init(){
        tile = new Tile(10, 10);
    }

    @Test
    void test_getHeight_returns_height_of_Tile(){
        assertEquals(Config.TILE_SIZE, tile.getHeight());
    }

    @Test
    void test_getWidth_returns_height_of_Tile(){
        assertEquals(Config.TILE_SIZE, tile.getWidth());
    }

    @Test
    void test_getX_returns_x_position_of_terrain(){
        assertEquals(10, tile.getX());
    }

    @Test
    void test_getY_returns_y_position_of_terrain(){
        assertEquals(10, tile.getY());
    }

    @Test
    void test_getG_returns_g_value_of_Tile(){
        assertEquals(Double.MAX_VALUE, tile.getG());
    }
    @Test
    void test_setG_sets_g_value_of_Tile(){
        tile.setG(10);
        assertEquals(10, tile.getG());
    }

    @Test
    void test_getF_returns_f_value_of_terrain(){
        assertEquals(Double.MAX_VALUE, tile.getF());
    }
    @Test
    void test_setF_sets_f_value_of_terrain(){
        tile.setF(10);
        assertEquals(10, tile.getF());
    }

    @Test
    void test_getParent_returns_null_on_new_Tile(){
        assertNull(tile.getParent());
    }

    @Test
    void test_setParent_sets_parent_Tile_to_Tile(){
        Tile testTile = new Tile(11, 11);
        tile.setParent(testTile);
        assertEquals(testTile, tile.getParent());
    }

    @Test
    void test_getParent_returns_parent_of_Tile(){
        Tile testTile = new Tile(11, 11);
        tile.setParent(testTile);
        boolean test = tile.getParent() == testTile;
        assertTrue(test);
    }


    @Test
    void test_isPassable_returns_true_for_new_Tile(){
        assertTrue(tile.isPassable());
    }

    @Test
    void test_setPassable_sets_boolean_to_false_on_Tile(){
        tile.setPassable(false);
        assertFalse(tile.isPassable());
    }
    @Test
    void test_setPassable_sets_boolean_to_true_on_Tile() {
        tile.setPassable(false);
        tile.setPassable(true);
        assertTrue(tile.isPassable());
    }

    @Test
    void test_getNeighbors_returns_empty_list_on_new_Tile(){
        assertTrue(tile.getNeighbors().isEmpty());
    }

    @Test
    void test_getNeighbors_returns_List_of_Edges(){
        List<Tile.Edge> testList = new ArrayList<>();
        assertEquals(testList.getClass(), tile.getNeighbors().getClass());
    }

    @Test
    void test_addBranch_adds_Edge_with_neighbor_to_Tile(){
        Tile testTile = new Tile(11, 11);
        tile.addBranch(1, testTile);

        assertEquals(testTile, tile.getNeighbors().get(0).getTile());
    }
    @Test
    void test_compareTo_compares_two_terrains_f_values_1_if_larger(){
        Tile testTile = new Tile(1,1);
        testTile.setF(1);
        tile.setF(2);
        assertEquals(1, tile.compareTo(testTile));
    }

    @Test
    void test_compareTo_compares_two_terrains_f_values_negative_1_if_smaller(){
        Tile testTile = new Tile(1,1);
        testTile.setF(3);
        tile.setF(2);
        assertEquals(-1, tile.compareTo(testTile));
    }

    @Test
    void test_getPos_returns_new_Vector2(){
        Vector2 testVector = new Vector2(1, 1);
        assertEquals(testVector.getClass(), tile.getPos().getClass());
    }
    @Test
    void test_getPos_returns_new_Vector2_with_same_x_value(){
        Vector2 testVector = new Vector2(480, 480);
        assertEquals(testVector.getX(), tile.getPos().getX());
    }
    @Test
    void test_getPos_returns_new_Vector2_with_same_y_value(){
        Vector2 testVector = new Vector2(480, 480);
        assertEquals(testVector.getY(), tile.getPos().getY());
    }

    @Test
    void test_getCenter_returns_center_vector_xpos_of_Tile(){
        Vector2 testVector = new Vector2(48+24, 48+24);
        Tile testTile = new Tile(1, 1);
        assertEquals(testVector.getX(), testTile.getCenter().getX());
    }

    @Test
    void test_getCenter_returns_new_center_vector_ypos_of_Tile(){
        Vector2 testVector = new Vector2(48+24, 48+24);
        Tile testTile = new Tile(1, 1);
        assertEquals(testVector.getY(), testTile.getCenter().getY());
    }

    @Test
    void test_Edge_class_in_Tile_creates_new_Edge(){
        Tile neighbor = new Tile(1, 1);
        tile.addBranch(1 , neighbor);
        Tile.Edge edge  = tile.getNeighbors().get(0);
        assertSame(Tile.Edge.class, edge.getClass());
    }
    @Test
    void test_getTile_in_Edge_returns_tile(){
        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(2, 2);
        tile1.addBranch(1,tile2);
        Tile.Edge edge = tile1.getNeighbors().get(0);
        assertEquals(tile2, edge.getTile());

    }
    @Test
    void test_getWeight_in_Edge_returns_weight(){
        int weight = 10;
        Tile tile1 = new Tile(1, 1);
        Tile tile2 = new Tile(2, 2);
        tile1.addBranch(weight,tile2);
        Tile.Edge edge = tile1.getNeighbors().get(0);
        assertEquals(weight, edge.getWeight());

    }
}
