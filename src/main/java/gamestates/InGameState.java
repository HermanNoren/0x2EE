package gamestates;

import main.Observer;
import sprites.HUD;
import sprites.Player;
import sprites.ShopSprite;
import sprites.Sprite;
import worldclasses.Map;

import java.util.ArrayList;

public class InGameState implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;
    private ArrayList<Observer> observers;
    private HUD hud;

    public InGameState() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        map = new Map();
        sprites.add(player);
        sprites.addAll(map.getTiles());
        hud = new HUD(player);
        observers = new ArrayList<>();
        observers.add(hud);
    }

    @Override
    public Player getPlayer() {
        return player;
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
