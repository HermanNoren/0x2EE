package shop;

import model.gameobjects.Player;
import model.gameobjects.Shop;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for seeing if the player is in correct position to shop. Background
 * information to know is that the player is 48 pixels
 * (Config.SPRITE_SIZE * 3) wide and tall. The shops size is 96*96 pixels,
 * twice as wide and tall as the player. The GameMap is 100 times a 100
 */
public class shopMethodsWorking {
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
    @Test
    void test_if_the_center_is_null(){

    }
}
