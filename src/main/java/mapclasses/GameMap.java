package mapclasses;

import config.Config;
import helperclasses.Vector2;
import sprites.ISprite;

import java.util.ArrayList;

public class GameMap {

    private final String[] gameMap = new String[] {
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                  WWWW                  ",
            "                     W                  ",
            "                     W                  ",
            "                     W                  ",
            "                      W                 ",
            "                      W                 ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "                                        ",
            "             WWWWWWWWWWWW               ",
            "                                        ",
            "                                        "
    };

    private ArrayList<ISprite> tiles;
    private ArrayList<ISprite> grass;

    public GameMap() {
        createMap();
    }

    public ArrayList<ISprite> getTiles() {
        return new ArrayList<>(tiles);
    }

    public ArrayList<ISprite> getGrass() {
        return new ArrayList<>(grass);
    }

    private void createMap() {
        tiles = new ArrayList<>();
        grass = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String row : gameMap) {
            for (char tile : row.toCharArray()) {
                switch(tile) {
                    case 'W':
                        tiles.add(new Terrain(new Vector2(x, y), false));
                    case ' ':
                        grass.add(new Terrain(new Vector2(x, y), true));
                }
                x += Config.SPRITE_SIZE;
            }
            x = 0;
            y += Config.SPRITE_SIZE;
        }
    }
}

