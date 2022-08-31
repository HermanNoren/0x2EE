package gamestates;

import sprites.Sprite;

import java.util.ArrayList;

public interface GameState {

    ArrayList<Sprite> getSprites();

    void update();

    void draw();

}
