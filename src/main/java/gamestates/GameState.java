package gamestates;

import view.Observer;
import sprites.Player;
import sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;

public interface GameState {

    ArrayList<Sprite> getSprites();

    void update();

    void draw();

}
