package mapclasses;

import config.Config;
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

    public GameMap() {
        createMap();
    }

    public ArrayList<ISprite> getTiles() {
        return new ArrayList<>(tiles);
    }

    private void createMap() {
        tiles = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String row : gameMap) {
            for (char tile : row.toCharArray()) {
                switch(tile) {
                    case 'W':
                        tiles.add(new Tile(x, y));
                }
                x += Config.SPRITE_SIZE;
            }
            x = 0;
            y += Config.SPRITE_SIZE;
        }
    }
}

