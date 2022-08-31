package gamestates;

import main.Observer;
import sprites.Sprite;

import java.util.ArrayList;

public interface GameState {

    ArrayList<Sprite> getSprites();

    ArrayList<Observer> getObservers();

    void update();

    void draw();

}
