package sprites;

import helperclasses.Rect;
import helperclasses.Vector2;

import java.awt.*;

public interface Sprite {

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
     * Returns the sprites' rect, which is used as a hitbox
     */

    Rect getRect();

    /**
     * Updates the sprite
     */
    void update();

    /**
     * Draws the sprite
     */
    void draw(Graphics g);
}
