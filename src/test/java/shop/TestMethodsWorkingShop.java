package shop;

import model.gameobjects.Shop;
import model.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for seeing if the player is in correct position to shop. Background
 * information to know is that the player is 48 pixels
 * (Config.SPRITE_SIZE * 3) wide and tall. The shops size is 96*96 pixels,
 * twice as wide and tall as the player.
 */
public class TestMethodsWorkingShop {
    public Shop shop;
   @BeforeEach
   void init(){
       shop = new Shop(100,100);
   }
   @Test
    void test_assert_that_getWidth_is_the_given_value(){
       assertEquals(shop.getWidth(), 96);
   }

   @Test
    void test_assert_that_getHeight_is_the_given_value(){
       assertEquals(shop.getHeight(), 96);
   }
   @Test
    void test_assert_that_the_Y_position_is_true_to_the_given_Y_position(){
       Vector2 pos = new Vector2(100,100);
       assertEquals(shop.getPos().getY(), pos.getY());
   }
    @Test
    void test_assert_that_the_X_position_is_true_to_the_given_X_position(){
        Vector2 pos = new Vector2(100,100);
        assertEquals(shop.getPos().getX(), pos.getX());
    }

    /**
     * takes first the height of the position (100) and the center of the
     * shop, this being 48 (96/2=48). Thus, the center for Y is 148.

     */
    @Test
    void test_assert_if_the_center_is_correct_through_calculations_Y(){
        Vector2 pos = new Vector2(148,148);
        assertEquals(pos.getY(), shop.getCenter().getY());
    }

    /**
     * Same argument as above.
     */
    @Test
    void test_assert_if_the_center_is_correct_through_calculations_X(){
        Vector2 pos = new Vector2(148,148);
        assertEquals(pos.getX(), shop.getCenter().getX());
    }
}
