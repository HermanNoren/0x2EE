package sprites;

import helperclasses.Vector2;

public abstract class Entity {

    Vector2 pos;
    Vector2 vel;
    Vector2 acc;
    int health;

    public Entity(int x, int y, int health){
        pos = new Vector2(x, y);
        vel = new Vector2(1, 1);
        acc = new Vector2(0, 0);
        this.health = health;
    }
}
