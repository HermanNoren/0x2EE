package sprites;

import helperclasses.Rect;
import helperclasses.Vector2;

import javax.swing.JPanel;
import java.awt.*;

public class Player implements Sprite {

    private int size = 36;
    private Rect rect;
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private int health;
    private int score;
    private int money;

    public Player(int x, int y) {
        rect = new Rect(size, size, x, y);
        pos = new Vector2(x, y);
        vel = new Vector2(1, 1);
        acc = new Vector2(0, 0);
        health = 100;
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

    @Override
    public Vector2 getPos() {
        return pos;
    }

    @Override
    public Rect getRect() {
        return rect;
    }

    @Override
    public void update() {
        pos.x += vel.x;
        pos.y += vel.y;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect((int) pos.x, (int) pos.y, size, size);
    }
}
