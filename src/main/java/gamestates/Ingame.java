package gamestates;

import sprites.Player;
import sprites.Sprite;
import worldclasses.Map;

import java.util.ArrayList;

public class Ingame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;

    public Ingame() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        sprites.add(player);
        map = new Map();
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        return new ArrayList<>(sprites);
    }

    @Override
    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    @Override
    public void draw() {

    }

}
