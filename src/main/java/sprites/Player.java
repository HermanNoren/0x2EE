package sprites;

import helperclasses.Rect;
import helperclasses.Vector2;
import config.config;

import java.awt.*;

public class Player extends Entity implements Sprite {

    private int score;
    private int money;

    public Player(int x, int y, int health) {
        super(x, y, health);
        score = 0;
        money = 0;
    }

    public int getScore(){
        return score;
    }

    public int getHealth(){
        return health;
    }

    public int getMoney(){
        return money;
    }

}
