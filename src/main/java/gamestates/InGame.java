package gamestates;

import sprites.Player;
import sprites.Sprite;
import worldclasses.Map;

import java.util.ArrayList;

public class InGame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;

    public InGame() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        map = new Map();

        sprites.add(player);
        sprites.addAll(map.getTiles());
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
