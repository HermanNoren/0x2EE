package view.drawers;

import config.Config;

import model.Vector2;
import view.ImageHandler;
import model.mapclasses.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import view.Camera;

public class MapDrawer implements IImageIteratorDrawer {
    private final BufferedImage[] tileImgs = new BufferedImage[3];
    private final BufferedImage[] treeImgs = new BufferedImage[3];
    private BufferedImage activeTreeImg;
    private int index;
    private Tile[][] gameMap;
    private  Camera camera;
    private int spriteSize = Config.SPRITE_SIZE*3;
    private ImageHandler imageHandler;

    public MapDrawer(Tile[][] gameMap){
        this.gameMap = gameMap;
        camera = Camera.getInstance();
        this.imageHandler = new ImageHandler();
        index = 0;
        initTileImgs();
        initTreeImgs();
        activeTreeImg = treeImgs[index];
    }

    private void initTileImgs(){
        tileImgs[0] = imageHandler.getImage("imgs/tile/grass/grass1.png");
        tileImgs[1] = imageHandler.getImage("imgs/tile/grass/grass2.png");
        tileImgs[2] = imageHandler.getImage("imgs/tile/grass/grass3.png");
    }

    private void initTreeImgs() {
        treeImgs[0] = imageHandler.getImage("imgs/tile/tree/tree1.png");
        treeImgs[1] = imageHandler.getImage("imgs/tile/tree/tree2.png");
        treeImgs[2] = imageHandler.getImage("imgs/tile/tree/tree3.png");
    }


    @Override
    public void draw(Graphics2D g2) {

        // Coordinates of tiles to paint with a 2 tile offset to guarantee visibility
        int left = (int) (camera.getCenter().getX() - Config.SCREEN_WIDTH/2)/spriteSize - 2;
        int right = (int) (camera.getCenter().getX() + Config.SCREEN_WIDTH/2)/spriteSize + 2;
        int up = (int) (camera.getCenter().getY() - Config.SCREEN_HEIGHT/2)/spriteSize - 2;
        int down = (int) (camera.getCenter().getY() + Config.SCREEN_HEIGHT/2)/spriteSize + 2;

        // If out of bounds
        if (left < 0) {
            left = 0;
        }
        if (up < 0) {
            up = 0;
        }

        Vector2 newTerrainVector;

        for (int col = left; col < right && col < Config.MAP_WIDTH; col++){
            for(int row = up; row < down && row < Config.MAP_HEIGHT; row++){
                newTerrainVector = new Vector2(gameMap[col][row].getPos()); // For drawing in correct place.

                List<Integer> drawInformation = DrawerHelper.
                    calculateDrawingInformation(
                            newTerrainVector,
                            gameMap[col][row].getWidth(),
                            gameMap[col][row].getHeight());
                if (gameMap[col][row].isPassable()) {
                    g2.drawImage(tileImgs[(row * col) % 3], drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                } else {
                    g2.drawImage(activeTreeImg, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
                }

                newTerrainVector.setX(newTerrainVector.getX() + Config.TILE_SIZE);
                newTerrainVector.setY(newTerrainVector.getY() + Config.TILE_SIZE);
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

