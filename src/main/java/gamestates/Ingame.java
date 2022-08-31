package gamestates;

import sprites.Player;
import sprites.ShopSprite;
import sprites.Sprite;

import java.util.ArrayList;

public class Ingame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private ShopSprite shop;

    public Ingame() {
        player = new Player(0, 0);
        shop = new ShopSprite(0,0);

        sprites = new ArrayList<>();
        sprites.add(player);
        sprites.add(shop);
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        return sprites;
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
