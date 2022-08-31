package gamestates;

import main.Observer;
import sprites.HUD;
import sprites.Player;
import sprites.ShopSprite;
import sprites.Sprite;
import worldclasses.Map;

import java.util.ArrayList;

public class InGame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;
    private ArrayList<Observer> observers;
    private HUD hud;
    private ShopSprite shop;
    private ShopSprite shop2;
    private ShopSprite shop3;

    public InGame() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        shop = new ShopSprite(100,100, 100, 100);       //
        shop2 = new ShopSprite(100,100, 250, 100);      // manligt organ
        shop3 = new ShopSprite(120,500, 175, 150);      //
        map = new Map();
        sprites.add(shop2);
        sprites.add(shop);
        sprites.add(shop3);
        sprites.add(player);
        sprites.addAll(map.getTiles());
        hud = new HUD(player);
        observers = new ArrayList<>();
        observers.add(hud);
    }

    @Override
    public ArrayList<Sprite> getSprites() {
        return new ArrayList<>(sprites);
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }


    @Override
    public void update() {
        for (Sprite sprite : sprites) {
            sprite.update();
        }

        for (Observer observer : observers){
            observer.update();
        }

    }

    @Override
    public void draw() {

    }

}
