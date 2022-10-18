package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ImageHandler {

    public static BufferedImage getImage(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;

    }

    public static void setImgs(int nrImgs, Map<String, BufferedImage> imgs, List<String> imgTypes) {
        for (String itemType : imgTypes) {
            for (int i = 0; i < nrImgs; i++) {
                imgs.put(itemType + i, getImage("imgs/drops/"+ itemType + "/" + i + ".png"));
            }
        }
    }
}
