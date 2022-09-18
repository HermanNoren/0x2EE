package mapclasses;

import config.Config;
import helperclasses.Vector2;
import sprites.ISprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Terrain> grass;
    public GameMap() {
        createMap();
    }

    public ArrayList<ISprite> getTiles() {
        return new ArrayList<>(tiles);
    }

    public HashMap<String, Terrain> getGrass() {
        return new HashMap<String, Terrain>(grass);
    }

    private void addNeighbors(Terrain nod){
        Vector2 vec = nod.getPos();
        int size = Config.SPRITE_SIZE;

        // Add left side neighbour
        String key = "" + ((int) vec.getX() + size) + (int) vec.getY();
        if (grass.get(key) != null) nod.addBranch(1, grass.get(key));

        // Add right side neighbour
        key = "" + ((int) vec.getX() - size) + (int) vec.getY();
        if (grass.get(key) != null) nod.addBranch(1, grass.get(key));

        // Add top neighbour
        key = "" + (int) vec.getX() + ((int) vec.getY() + size);
        if (grass.get(key) != null) nod.addBranch(1, grass.get(key));

        // Add bottom neighbour
        key = "" + (int) vec.getX() + ((int) vec.getY() - size);
        if (grass.get(key) != null) nod.addBranch(1, grass.get(key));

        System.out.println("My x-pos:" + nod.getPos().getX());
        System.out.println("My y-pos:" + nod.getPos().getX());
        for (Terrain.Edge terrain : nod.neighbors){
            System.out.println("Neighbour x-pos:" + terrain.node.getPos().getX() + "\nNeighbour y-pos:" + terrain.node.getPos().getY());
        }
        System.out.println("-----------------");

    }

    private void createMap() {
        tiles = new ArrayList<>();
        grass = new HashMap<>();
        int x = 0;
        int y = 0;
        for (String row : gameMap) {
            for (char tile : row.toCharArray()) {
                Vector2 vec = new Vector2(x, y);
                String key = "" + x + y;
                switch(tile) {
                    case 'W':
                        tiles.add(new Terrain(vec, false));
                    case ' ':
                        Terrain terrain = new Terrain(vec, true);
                        grass.put(key, terrain);
                }
                x += Config.SPRITE_SIZE;
            }
            x = 0;
            y += Config.SPRITE_SIZE;
        }
        grass.forEach((key, tile) -> addNeighbors(tile));
    }
}

