package gamestates;

import sprites.Player;
import sprites.Sprite;

import java.util.ArrayList;

public class Ingame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;

    public Ingame() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        sprites.add(player);
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
