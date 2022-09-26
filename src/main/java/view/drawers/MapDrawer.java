package view.drawers;

import config.Config;

import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.helperclasses.ImageHandler;
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

    private ImageHandler imageHandler;

    public MapDrawer(GameMap gameMap){
        this.gameMap = gameMap;
        this.terrains = gameMap.getTerrains();
        this.imageHandler = new ImageHandler();
        loadTerrainImages();
    }

    private void loadTerrainImages(){
        terrainImgs[0] = imageHandler.getImage("imgs/tile/grass.png");
        terrainImgs[1] = imageHandler.getImage("imgs/tile/tree.png");
        terrainImgs[2] = imageHandler.getImage("imgs/tile/wall.png");
        terrainImgs[3] = imageHandler.getImage("imgs/tile/border.png");
    }

    private void setupImg(String path, int imgIndex){
        terrainImgs[imgIndex] = setImage("imgs/tile/"+path+".png");
    }


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

                List<Integer> drawInformation = DrawerHelper.
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

