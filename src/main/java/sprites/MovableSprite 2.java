package sprites;

import controllers.Direction;

/**
 * Movable sprites
 */
public interface Movable {
    /**
     * @param direction
     * Used to move movable sprites.
     */
    void move(Direction direction);
}
