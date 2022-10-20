package model.gameobjects;


import model.Vector2;

/**
 * Interface all game-objects with a position and size should implement
 */
public interface IGameObject {
    /**
     * Method used for returning the position of the sprite.
     * @return
     */

    Vector2 getPos();
    /**
     * Method used to get the center position of the sprite
     * @return Center position
     */
    Vector2 getCenter();
    /**
     * Method used get the width of the GameObject
     * @return the width of the sprite
     */
    int getWidth();
    /**
     * Method used to get the height of the
     * @return the height of the gameObject
     */
    int getHeight();
}
