package view.drawers;

import config.Config;

import model.Game;
import model.gameobjects.IGameObject;
import model.gameobjects.Player;
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
    private final Player player;
    private final int[][] mapNums;

    public MapDrawer(Game game){

        this.gameMap = game.getGameMap();
        this.player = game.getPlayer();
        mapNums = new int[gameMap.getWidth()][gameMap.getHeight()];
        this.terrains = gameMap.getTerrains();
        loadTerrainImages();
        loadGameMap("maps/map1.txt");
    }

    private void loadTerrainImages(){
        terrainImgs[0] = setImage("imgs/tile/grass.png");
        terrainImgs[1] = setImage("imgs/tile/tree.png");
        terrainImgs[2] = setImage("imgs/tile/wall.png");
        terrainImgs[3] = setImage("imgs/tile/border.png");
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

    private BufferedImage setImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public void draw(Graphics2D g2) {
        int spriteSize = Config.SPRITE_SIZE*3;
        int x = 0;
        int y = 0;

        // Coordinates of tiles to paint with a 5 tile offset to guarantee visibility
        int left = (int) (player.getPosX() - Config.SCREEN_WIDTH/2)/spriteSize - 5;
        int right = (int) (player.getPosX() + Config.SCREEN_WIDTH/2)/spriteSize + 5;
        int up = (int) (player.getPosY() - Config.SCREEN_HEIGHT/2)/spriteSize - 5;
        int down = (int) (player.getPosY() + Config.SCREEN_HEIGHT/2)/spriteSize + 5;

        // If out of bounds
        if (left < 0)
            left = 0;
        if (up < 0)
            up = 0;

        int col = left;
        int row = up;
        Terrain[][] gameMapCoordinates = gameMap.getGameMapCoordinates();

        while (col <= right && row <= down) {

            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(gameMap.getGameMapCoordinates()[col][row].getPos(),
                   gameMapCoordinates[col][row].getWidth(), gameMapCoordinates[col][row].getHeight());

            int terrainNum = mapNums[col][row];

            g2.drawImage(terrainImgs[terrainNum], drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);

            x += spriteSize;

            col++;

            if (col == right || col == gameMap.getWidth()) {
                if (row == gameMap.getHeight())
                    break;
                col = left;
                x= 0;
                y+=spriteSize;
                row++;
            }

        }


    }
}

