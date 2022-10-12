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
    private final int x_position;
    private final int y_position;

    public Vector2 position;

    /**
     * The 2 is made to represent that the hit-box of the shop is twice
     * as wide and long as it is represented by two pictures in both x and y directions.
     * However, the Config.SPRITE_SIZE * 3 is done to scale the program properly.
     */
    public final static int width  = Config.SPRITE_SIZE * 3 * 2;
    public final static int height = width;

    /**
     * A boolean used to see if the player is on the store.
     */

    public boolean playerOnShop = false;

    public  Shop(int x_position, int y_position){
        this.x_position = x_position - width * 2;
        this.y_position = y_position - height * 2;
        this.position = new Vector2(x_position, y_position);
    }


    /**
     * Returns the width of the store.
     * @return the store's width
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the store.
     * @return the store's height.
     */

    @Override
    public int getHeight() {
        return height;
    }

    /**
     States the position of the shop on the map.
     */
    @Override
    public Vector2 getPos() {
        return new Vector2(this.position);
    }

    /**
     * Method used to get the center of the shop.
     * @return the center of the shop.
     */
    @Override
    public Vector2 getCenter() {
        double x = x_position + (double) (getWidth() / 2);
        double y = y_position + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

}
