package view.drawers;

import config.Config;

import model.Vector2;
import view.ImageHandler;
import model.mapclasses.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import view.Camera;

/**
 * Used to draw the GameMap Tiles.
 * @author Arthur Alexandersson, Herman Noren
 */
public class MapDrawer implements IImageIteratorDrawer {
    private final BufferedImage[] tileImgs = new BufferedImage[3];
    private final BufferedImage[] treeImgs = new BufferedImage[3];
    private BufferedImage activeTreeImg;
    private int index;
    private final Tile[][] gameMap;
    private Camera camera;
    private int tileSize = Config.TILE_SIZE;

    public MapDrawer(Tile[][] gameMap){
        this.gameMap = gameMap.clone();
        camera = Camera.getInstance();
        index = 0;
        initTileImgs();
        initTreeImgs();
        activeTreeImg = treeImgs[index];
    }

    private void initTileImgs(){
        tileImgs[0] = ImageHandler.getImage("imgs/tile/grass/grass1.png");
        tileImgs[1] = ImageHandler.getImage("imgs/tile/grass/grass2.png");
        tileImgs[2] = ImageHandler.getImage("imgs/tile/grass/grass3.png");
    }

    private void initTreeImgs() {
        treeImgs[0] = ImageHandler.getImage("imgs/tile/tree/tree1.png");
        treeImgs[1] = ImageHandler.getImage("imgs/tile/tree/tree2.png");
        treeImgs[2] = ImageHandler.getImage("imgs/tile/tree/tree3.png");
    }


    @Override
    public void draw(Graphics2D g2) {
        // Coordinates of tiles to paint with a 2 tile offset to guarantee visibility
        int left = (int) (camera.getCenter().getX() - Config.SCREEN_WIDTH/2)/ tileSize - 2;
        int right = (int) (camera.getCenter().getX() + Config.SCREEN_WIDTH/2)/ tileSize + 2;
        int up = (int) (camera.getCenter().getY() - Config.SCREEN_HEIGHT/2)/ tileSize - 2;
        int down = (int) (camera.getCenter().getY() + Config.SCREEN_HEIGHT/2)/ tileSize + 2;

        // If out of bounds
        if (left < 0) {
            left = 0;
        }
        if (up < 0) {
            up = 0;
        }
        for (int col = left; col < right && col < Config.MAP_WIDTH; col++){
            for(int row = up; row < down && row < Config.MAP_HEIGHT; row++){
                List<Integer> drawInformation = DrawerHelper.
                    calculateDrawingInformation(
                            gameMap[col][row].getPos(),
                            gameMap[col][row].getWidth(),
                            gameMap[col][row].getHeight());
                if (gameMap[col][row].isPassable()) {
                    g2.drawImage(tileImgs[(row * col) % 3], drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                } else {
                    g2.drawImage(activeTreeImg, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                }
            }
        }

    }

    @Override
    public void switchImage() {
        activeTreeImg = treeImgs[index];
        index++;
        index %= 3;
    }
}

