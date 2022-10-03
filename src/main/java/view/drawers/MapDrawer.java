package view.drawers;

import config.Config;

import model.Game;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.gameobjects.Player;
import model.helperclasses.ImageHandler;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import view.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MapDrawer implements IDrawer {
    private final List<Terrain> terrains;
    private final BufferedImage[] terrainImgs = new BufferedImage[10];
    private final GameMap gameMap;
    private final Camera camera;
    private final int[][] mapNums;
    private int size = 48;
    private int spriteSize = Config.SPRITE_SIZE*3;
    private ImageHandler imageHandler;
    private Game game;
    private Player player;

    public MapDrawer(Game game){
        this.gameMap = game.getGameMap();
        this.game = game;
        player = game.getPlayer();
        camera = Camera.getInstance();
        mapNums = new int[gameMap.getWidth()][gameMap.getHeight()];
        this.terrains = gameMap.getTerrains();
        this.imageHandler = new ImageHandler();
        loadTerrainImages();
    }

    private void loadTerrainImages(){
        terrainImgs[0] = ImageHandler.scaleImage(imageHandler.getImage("imgs/tile/grass.png"), spriteSize, spriteSize);
        terrainImgs[1] = ImageHandler.scaleImage(imageHandler.getImage("imgs/tile/border.png"), spriteSize, spriteSize);
        terrainImgs[2] = ImageHandler.scaleImage(imageHandler.getImage("imgs/tile/tree.png"), spriteSize, spriteSize);
        terrainImgs[3] = ImageHandler.scaleImage(imageHandler.getImage("imgs/tile/wall.png"), spriteSize, spriteSize);
    }


    @Override
    public void draw(Graphics2D g2) {

        // Coordinates of tiles to paint with a 5 tile offset to guarantee visibility
        int left = (int) (camera.getCenter().getX() - Config.SCREEN_WIDTH/2)/spriteSize -5;
        int right = (int) (camera.getCenter().getX() + Config.SCREEN_WIDTH/2)/spriteSize +5;
        int up = (int) (camera.getCenter().getY() - Config.SCREEN_HEIGHT/2)/spriteSize -5;
        int down = (int) (camera.getCenter().getY() + Config.SCREEN_HEIGHT/2)/spriteSize +5;

        // If out of bounds
        if (left < 0) {
            left = 0;
        }
        if (up < 0) {
            up = 0;
        }
        int terrainSize = Config.SPRITE_SIZE*3;

        Vector2 newTerrainVector;
        Terrain[][] gameMapCoordinates = gameMap.getGameMapCoordinates();

        for (int col = left; col < right && col < gameMap.getWidth(); col++){
            for(int row = up; row < down && row < gameMap.getHeight(); row++){
                newTerrainVector = new Vector2(gameMapCoordinates[col][row].getPos()); // For drawing in correct place.

                List<Integer> drawInformation = DrawerHelper.
                    calculateDrawingInformation(
                            newTerrainVector,
                            gameMapCoordinates[col][row].getWidth(),
                            gameMapCoordinates[col][row].getHeight());

                int terrainNum = gameMapCoordinates[col][row].getTerrainType();
                g2.drawImage(terrainImgs[terrainNum], drawInformation.get(0), drawInformation.get(1), null);

                newTerrainVector.setX(newTerrainVector.getX() + terrainSize);
                newTerrainVector.setY(newTerrainVector.getY() + terrainSize);
            }
        }


    }
}

