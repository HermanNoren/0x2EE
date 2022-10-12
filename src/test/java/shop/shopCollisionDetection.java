package shop;

import model.Game;
import model.gameobjects.Player;
import model.gameobjects.Shop;
import model.mapclasses.GameMap;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test for seeing if the player is in correct position to shop. Background
 * information to know is that the player is 48 pixels
 * (Config.SPRITE_SIZE * 3) wide and tall. The shops size is 96*96 pixels,
 * twice as wide and tall as the player. The GameMap is 100 times a 100
 */
public class shopCollisionDetection {
    private Shop shop;
    private Player player;
    private GameMap gameMap = new GameMap(100, 100);
    @BeforeEach
    void init(){
        shop = new Shop(0,0);
        //player = new Player(1,1, )
    }
}
