package model.gameobjects;

import config.Config;
import model.helperclasses.Vector2;


/**
 * This is the shop where weapons and armor can be
 * upgraded through the game. It also contains logic regarding
 * when the player is in range to enter the shop.
 * The shop consists of 4 16x16 images.
 * */


public class Shop implements IGameObject {
    public int x_position;
    public int y_position;

    public Vector2 position;

    /**
     * The 2 is made to represent that the hit-box of the shop is twice
     * as wide and long as it is represented by two pictures in both x and y directions.
     */
    public final static int width  = Config.SPRITE_SIZE * 3 * 2;
    public final static int height = width;


    public boolean playerOnShop = false;

    public  Shop(int x_position, int y_position){
        this.x_position = x_position - width*2;
        this.y_position = y_position - height*2;
        this.position = new Vector2(x_position, y_position);
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
        return new Vector2(this.position);
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
    public void update(double dt) {

    }
}
