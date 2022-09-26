package utility;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class used to scale images compiletime, improves performance.
 */
public class ImageScaler {

    public static BufferedImage scaleImage(BufferedImage original, int width, int height ) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
