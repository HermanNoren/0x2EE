package gamestates;

import view.Observer;
import sprites.Player;
import sprites.Sprite;

import java.io.IOException;
import java.util.ArrayList;

public interface GameState {
    String getStateTag();
    void update();
}
