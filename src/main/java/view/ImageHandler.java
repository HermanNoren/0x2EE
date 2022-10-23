package view;

import model.gameobjects.EDirection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing static methods to get Buffered images, or Map with buffered images.
 * Used in drawers to load correct images.
 * @author Arthur Alexandersson, Rickard Leksell
 */
public class ImageHandler {

    public static BufferedImage getImage(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("src/main/resources/"+path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;

    }

    /**
     * Used to get a Map to match certain strings with actual images
     * @param nrImgs amount of images
     * @param imgTypes strings
     * @return Map with certain strings as keys, and images as values
     */
    public static Map<String, BufferedImage> getImgs(int nrImgs, List<String> imgTypes) {
        Map<String, BufferedImage> imgs = new HashMap<>();
        for (String imgType : imgTypes) {
            for (int i = 0; i < nrImgs; i++) {
                imgs.put(imgType + i, getImage("imgs/"+imgType+"/" + i + ".png"));
            }
        }
        return imgs;
    }

    /**
     * @param nrImgs total images
     * @param imgTypes type of image
     * @param directions direction, up, down, left, right.
     * @return a Map containing a map.
     */
    public static Map<EDirection, Map<String, BufferedImage>> getImgsWithDirections(int nrImgs, List<String> imgTypes, List<EDirection> directions) {
        Map<EDirection, Map<String, BufferedImage>> retMap = new HashMap<>();
        for (EDirection direction: directions){
            retMap.put(direction, getImgMap(nrImgs, imgTypes, direction));
        }
        return retMap;
    }

    private static Map<String, BufferedImage> getImgMap(int nrImgs, List<String> imgTypes, EDirection direction){
        Map<String, BufferedImage> tempMap = new HashMap<>();
        for (String imgType: imgTypes){
            for (int i = 1; i < nrImgs+1; i++) {
                tempMap.put(imgType + (i - 1), getImage("imgs/"+imgType+"/"+direction.toString().toLowerCase()+"_"+i+".png"));
            }
        }
        return tempMap;

    }

}
