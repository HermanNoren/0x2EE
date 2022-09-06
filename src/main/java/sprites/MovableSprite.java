package sprites;

import controllers.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/**
 * MovableSprite sprites
 */
public interface MovableSprite {
    /**
     * @param direction
     * Used to move movable sprites.
     */
    void move(Direction direction);
    void setImages(ArrayList<String> imagePath);

}
