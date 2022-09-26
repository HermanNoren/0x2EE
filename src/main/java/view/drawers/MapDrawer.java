package view.drawers;

import config.Config;

import model.gameobjects.IGameObject;
import model.helperclasses.ImageHandler;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import view.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MapDrawer implements IDrawer {
    private final ArrayList<IGameObject> terrains;
    private final BufferedImage[] terrainImgs = new BufferedImage[10];
    private final GameMap gameMap;
    private final int[][] mapNums;

    private ImageHandler imageHandler;

    public MapDrawer(GameMap gameMap){

        this.gameMap = gameMap;
        mapNums = new int[gameMap.getWidth()][gameMap.getHeight()];
        this.terrains = gameMap.getTerrains();
        this.imageHandler = new ImageHandler();
        loadTerrainImages();
        loadGameMap("maps/map1.txt");
    }

    private void loadTerrainImages(){
        terrainImgs[0] = imageHandler.getImage("imgs/tile/grass.png");
        terrainImgs[1] = imageHandler.getImage("imgs/tile/tree.png");
        terrainImgs[2] = imageHandler.getImage("imgs/tile/wall.png");
        terrainImgs[3] = imageHandler.getImage("imgs/tile/border.png");
    }

    private void loadGameMap(String path){
        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            int col = 0;
            int row = 0;
            while (col<gameMap.getWidth() && row < gameMap.getHeight()){
                String line = br.readLine();
                System.out.println(line);
                while (col < gameMap.getWidth()){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapNums[col][row] = num;
                    col++;
                }
                if (col == gameMap.getWidth()){
                    col=0;
                    row++;

                }
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void draw(Graphics2D g2) {
        int spriteSize = Config.SPRITE_SIZE*3;
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;
        Terrain[][] gameMapCoordinates = gameMap.getGameMapCoordinates();
        while (col < gameMap.getWidth() && row < gameMap.getHeight()) {

            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(gameMap.getGameMapCoordinates()[col][row].getPos(),
                   gameMapCoordinates[col][row].getWidth(), gameMapCoordinates[col][row].getHeight());

            int terrainNum = mapNums[col][row];

            g2.drawImage(terrainImgs[terrainNum], drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);

            x += spriteSize;

            col++;

            if (col == gameMap.getWidth()) {
                col = 0;
                x= 0;
                y+=spriteSize;
                row++;
            }

        }


    }
}

