package model.gameobjects;


import model.helperclasses.Rect;
import model.helperclasses.Vector2;

public interface IGameObject {

    Vector2 getPos();

    /**
     * Returns the width of the sprite
     */
    int getWidth();

    /**
     * Returns the height of the sprite
     */
    int getHeight();

    /**
     * Returns the position of the upper left corner
     * of the sprite.
     */

    /**
     * Returns the center position of the sprite
     * @return Center position
     */
    Vector2 getCenter();



    boolean isPassable();
    /**
     * Updates the sprite
     */
    void update(double dt);
}
