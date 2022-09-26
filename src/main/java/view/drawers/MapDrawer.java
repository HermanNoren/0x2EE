package view.drawers;

import config.Config;

import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import utility.ImageScaler;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import java.util.List;

public class MapDrawer implements IDrawer {
    private final List<IGameObject> terrains;
    private final BufferedImage[] terrainImgs = new BufferedImage[10];
    private final GameMap gameMap;
    private int size = 48;

    public MapDrawer(GameMap gameMap){
        this.gameMap = gameMap;
        this.terrains = gameMap.getTerrains();
        loadImgs();
    }

    private void loadImgs(){
        setupImg("grass", 0);
        setupImg("border", 1);
        setupImg("wall", 2);
        setupImg("tree", 3);
    }

    private void setupImg(String path, int imgIndex){
        terrainImgs[imgIndex] = setImage("imgs/tile/"+path+".png");
    }


    private BufferedImage setImage(String path){
        BufferedImage image;
        File file = new File(path);
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image = ImageScaler.scaleImage(image, size, size);
        return image;
    }





    /**
     * Draws the game map
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2) {
        int terrainSize = Config.SPRITE_SIZE*3;

        Vector2 newTerrainVector;
        Terrain[][] gameMapCoordinates = gameMap.getGameMapCoordinates();

        for (int col = 0; col < gameMap.getWidth(); col++){
            for(int row = 0; row < gameMap.getHeight(); row++){
                newTerrainVector = new Vector2(gameMapCoordinates[col][row].getPos()); // For drawing in correct place.
                newTerrainVector.x*=terrainSize;
                newTerrainVector.y*=terrainSize;

                ArrayList<Integer> drawInformation = DrawerHelper.
                    calculateDrawingInformation(
                            newTerrainVector,
                            gameMapCoordinates[col][row].getWidth(),
                            gameMapCoordinates[col][row].getHeight());

                int terrainNum = gameMapCoordinates[col][row].getTerrainType();
                g2.drawImage(terrainImgs[terrainNum], drawInformation.get(0), drawInformation.get(1), null);
            }
        }


    }
}

