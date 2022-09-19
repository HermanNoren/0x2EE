package mapclasses;

import config.Config;
import helperclasses.Vector2;
import sprites.ISprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMap {

    private final String[] gameMap = new String[] {
            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W                 WWWW                  ",
            "W                    W                  ",
            "W                    W                  ",
            "W                    W                  ",
            "W                     W                 ",
            "W                     W                 ",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W                                       ",
            "W            WWWWWWWWWWWW               ",
            "W                                       ",
            "W                                       "
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAWWWWWWWWWWWWWWAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"



    };

    private ArrayList<ISprite> tiles;
    private Map<String, Terrain> grass;

    private ArrayList<Terrain> path;

    public GameMap() {
        createMap();

    }

    public ArrayList<ISprite> getTiles() {
        return new ArrayList<>(tiles);
    }

    public HashMap<String, Terrain> getGrass() {
        return new HashMap<String, Terrain>(grass);
    }

    public ArrayList<Terrain> getPath() {
        return path;
    }

    /**
     * Sets the ArrayList<Terrain> to the given path. Used to update the current path from start node to target node.
     * @param path
     */
    public void setPath(ArrayList<Terrain> path) {
        this.path = path;
    }

    /**
     * Add neighbor to the given node. If the presumed neighbor isn't grass then it won't be added as a neighbor.
     * @param node
     */
    private void addNeighbors(Terrain node){
        Vector2 vec = node.getPos();
        int size = Config.SPRITE_SIZE;

        // Add left side neighbour
        String key = "" + ((int) vec.getX() + size) + (int) vec.getY();
        if (node.isPassable()){
            if (grass.get(key) != null) node.addBranch(1, grass.get(key));
        }

        // Add right side neighbour
        key = "" + ((int) vec.getX() - size) + (int) vec.getY();
        if(node.isPassable()){
            if (grass.get(key) != null) node.addBranch(1, grass.get(key));
        }

        // Add top neighbour
        key = "" + (int) vec.getX() + ((int) vec.getY() + size);
        if (node.isPassable()){
            if (grass.get(key) != null) node.addBranch(1, grass.get(key));
        }

        // Add bottom neighbour
        key = "" + (int) vec.getX() + ((int) vec.getY() - size);
        if(node.isPassable()){
            if (grass.get(key) != null) node.addBranch(1, grass.get(key));
        }

//        System.out.println("My x-pos:" + node.getPos().getX());
//        System.out.println("My y-pos:" + node.getPos().getX());
//        for (Terrain.Edge terrain : node.neighbors){
//            System.out.println("Neighbour x-pos:" + terrain.node.getPos().getX() + "\nNeighbour y-pos:" + terrain.node.getPos().getY());
//        }
//        System.out.println("-----------------");

    }

    /**
     * Creates the gamemap. Strings == W are set as obstacles, non-passable terrain, and strings == " "
     * are set as grass, passable terrains.
     */
    private void createMap() {
        tiles = new ArrayList<>();
        grass = new HashMap<>();
        path = new ArrayList<>();

        int x = 0;
        int y = 0;
        for (String row : gameMap) {
            for (char tile : row.toCharArray()) {
                Vector2 vec = new Vector2(x, y);
                String key = "" + x + y;

                switch(tile) {
                    case 'W':
                        tiles.add(new Tile(x, y));
                        tiles.add(new Terrain(vec, false));
                    case 'A':
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

