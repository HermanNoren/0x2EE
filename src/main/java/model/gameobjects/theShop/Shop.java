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
    public final static int x_position = 400;
    public final static int y_position = 200;
    public final static int width = 64*3; // make multiple of 64
    public final static int height = 56*3;  //multiple of 56

    public final static Vector2 pos = new Vector2(x_position, y_position);


    public  Shop(){

    }


    public final static Rect rect = new Rect(x_position, y_position, width, height);



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


    public void openShopPanel(){
        System.out.println("We're in boys");        //Make the shop light up!
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public boolean isPassable() {
        return false;
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
