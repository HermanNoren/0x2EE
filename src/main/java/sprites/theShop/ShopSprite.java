package sprites.theShop;

import helperclasses.Rect;
import helperclasses.Vector2;
import sprites.Sprite;

import java.awt.*;

/**
 * This Sprite is the shop where weapons and armor can be
 * upgraded through the game. It will also contain logic regarding
 * when the player is in range to enter the shop (more logic to come).
 */
public class ShopSprite implements Sprite {
    public final static int x_position = 0;
    public final static int y_position = 0;
    private Rect shopAreaSize;
    private Vector2 Position;


    public final static int sizeLength = 25;

    /**
     * The shop will have different parameters for its location and
     * its size. This is to make the player be able to shop and not
     * having to stand on top of the shop to do so. (Could actually
     * be a fun implementation maybe?)
     * @param x_size
     * @param y_size
     * @param x_position
     * @param y_position
     */
    public ShopSprite(int x_size, int y_size, int x_position, int y_position) {
        shopAreaSize = new Rect(100, 100,x_size , y_size);
        Position = new Vector2(x_position, y_position);
    }

    /**
     * will communicate through game if the player
     * is close enough to enter the shop, will then
     * switch to another panel where the shop
     * is located.
     * The player's coordinates are
     * taken as an argument and compared to the shops'
     * position.
     * If the player is close enough to the shop, the
     * openShopPanel will then be called, else an exception
     * will be thrown and an error message will be prompted
     * on the player's screen.
     */
    public void closeEnoughToShop(Vector2 position){
         if(getRect().intersects(this.Position, position))
             openShopPanel();
         //throw exception not close enough

    }


    public void openShopPanel(){

    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    /**
     States the position of the shop on the map, one size as it has the same length as height.
     */
    @Override
    public Vector2 getPos() {
        return this.Position;
    }

    @Override
    public Rect getRect() {
        return this.shopAreaSize;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillRect( x_position, y_position, sizeLength, sizeLength);
    }
}
