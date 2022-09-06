package sprites;

import controllers.Direction;

/**
 * MovableSprite sprites
 */
public interface MovableSprite {
    /**
     * @param direction
     * Used to move movable sprites.
     */
    void move(Direction direction);
}
