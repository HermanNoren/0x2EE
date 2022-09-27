package model.gameobjects;


import model.helperclasses.Rect;
import model.helperclasses.Vector2;

public interface IGameObject {

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
    Vector2 getPos();

    /**
     * Returns the center position of the sprite
     * @return Center position
     */
    Vector2 getCenter();

    /**
     * Returns the sprites' rect, which is used as a hitbox
     */
    Rect getRect();

    boolean isPassable();
    /**
     * Updates the sprite
     */
    void update();
}
