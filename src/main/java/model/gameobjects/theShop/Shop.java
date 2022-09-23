package model.gameobjects.theShop;

import model.helperclasses.Rect;
import model.helperclasses.Vector2;
import model.gameobjects.IGameObject;


/**
 * This ISprite is the shop where weapons and armor can be
 * upgraded through the game. It will also contain logic regarding
 * when the player is in range to enter the shop (more logic to come).
 */


public class Shop implements IGameObject {

    public  Shop(){}
    public final static int x_position = 300;
    public final static int y_position = 100;

    public final static Vector2 pos = new Vector2(x_position, y_position);
    public final static int width = 160;

    public final static int height = 160;

    public final static Rect rect = new Rect(x_position, y_position, width, height);


    public final static Vector2 static_position = new Vector2(x_position,y_position);
    public final static Rect static_size = new Rect(x_position, y_position, width, height);
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
         if(getRect().intersects(this.getPos(), position))
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
        return this.pos;
    }

    @Override
    public Vector2 getCenter() {
        return null;
    }

    @Override
    public Rect getRect() {
        return this.rect;
    }

    @Override
    public void update() {

    }

    /*
    @Override
    public void draw(Graphics2D g2) {
        g2.fillRect( x_position, y_position, sizeLength, sizeLength);
    }

     */
}
