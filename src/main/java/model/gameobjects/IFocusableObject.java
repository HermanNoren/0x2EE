package model.gameobjects;

import model.Vector2;

/**
 * Used for the camera to see the center of the game object.
 */
public interface IFocusableObject {
    /**
     * Returns the center position of the sprite
     * @return Center position
     */
    Vector2 getCenter();
}
