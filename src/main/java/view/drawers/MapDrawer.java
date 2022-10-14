package view.drawers;

import config.Config;

import model.Game;
import model.gameobjects.Player;
import model.helperclasses.Vector2;
import model.helperclasses.ImageHandler;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import view.Camera;

public class MapDrawer implements IDrawer, IIteratedImageDrawer {
    private final BufferedImage[] tileImgs = new BufferedImage[10];
    private Tile[][] map;
    private  Camera camera;
    private int spriteSize = Config.SPRITE_SIZE*3;
    private ImageHandler imageHandler;

    public MapDrawer(Tile[][] map){
        this.map = map;
        camera = Camera.getInstance();
        this.imageHandler = new ImageHandler();
        initTileImgs();
    }

    private void initTileImgs(){
        tileImgs[0] = imageHandler.getImage("imgs/tile/tree/tree1.png");
        tileImgs[1] = imageHandler.getImage("imgs/tile/tree/tree2.png");
        tileImgs[2] = imageHandler.getImage("imgs/tile/tree/tree3.png");

        tileImgs[3] = imageHandler.getImage("imgs/tile/grass/grass1.png");
        tileImgs[4] = imageHandler.getImage("imgs/tile/grass/grass2.png");
        tileImgs[5] = imageHandler.getImage("imgs/tile/grass/grass3.png");

        tileImgs[6] = imageHandler.getImage("imgs/tile/border.png");
    }


    @Override
    public void draw(Graphics2D g2) {

        // Coordinates of tiles to paint with a 5 tile offset to guarantee visibility
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
                newTerrainVector = new Vector2(map[col][row].getPos()); // For drawing in correct place.

                List<Integer> drawInformation = DrawerHelper.
                    calculateDrawingInformation(
                            newTerrainVector,
                            map[col][row].getWidth(),
                            map[col][row].getHeight());

                int terrainNum;
                if(map[col][row].isPassable()){
                    terrainNum = 3;
                }else {
                    terrainNum = 0;
                }
                g2.drawImage(tileImgs[terrainNum], drawInformation.get(0), drawInformation.get(1),
                        drawInformation.get(2), drawInformation.get(3), null);

                newTerrainVector.setX(newTerrainVector.getX() + Config.TILE_SIZE);
                newTerrainVector.setY(newTerrainVector.getY() + Config.TILE_SIZE);
            }
        }

    }

    @Override
    public void switchImage() {

    }
}

