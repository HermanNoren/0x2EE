package gamestates;

import main.Observer;
import sprites.HUD;
import sprites.Player;
import sprites.Sprite;
import worldclasses.Map;

import java.util.ArrayList;

public class Ingame implements GameState{

    private Player player;
    private ArrayList<Sprite> sprites;
    private Map map;
    private ArrayList<Observer> observers;
    private HUD hud;

    public Ingame() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        sprites.add(player);
        map = new Map();
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
