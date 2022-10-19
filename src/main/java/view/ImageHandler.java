package view;

import model.gameobjects.EDirection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

    public static Map<String, BufferedImage> getImgs(int nrImgs, List<String> imgTypes) {
        Map<String, BufferedImage> imgs = new HashMap<>();
        for (String imgType : imgTypes) {
            for (int i = 0; i < nrImgs; i++) {
                imgs.put(imgType + i, getImage("imgs/"+imgType+"/" + i + ".png"));
            }
        }
        return imgs;
    }

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
                tempMap.put(imgType+(i-1), getImage("imgs/"+imgType+"/"+direction+"_"+i+".png"));
            }
        }
        return tempMap;

    }

}
