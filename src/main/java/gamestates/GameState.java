package gamestates;

import main.Observer;
import sprites.Player;
import sprites.Sprite;

import java.util.ArrayList;

public interface GameState {

    Player getPlayer();

    ArrayList<Sprite> getSprites();

    ArrayList<Observer> getObservers();

    void update();

    void draw();

}
