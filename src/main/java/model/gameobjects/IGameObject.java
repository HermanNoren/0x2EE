package model.gameobjects;


import model.helperclasses.Vector2;

public interface IGameObject {

    Vector2 getPos();
    /**
     * Returns the center position of the sprite
     * @return Center position
     */
    Vector2 getCenter();
    /**
     * Returns the width of the sprite
     */
    int getWidth();

    /**
     * Returns the height of the sprite
     */
    int getHeight();

    int getSize();
    /**
     * Returns the position of the upper left corner
     * of the sprite.
     */


}
