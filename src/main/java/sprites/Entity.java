package sprites;

import config.Config;
import controllers.EDirection;
import helperclasses.Rect;
import helperclasses.Vector2;

/**
 * The IEnemy class contains logic to represent the sprite,
 * for example by containing information regarding its position
 * and health the program can determine if an entity is in range
 * to take damage thus if reduces its health.
 */

public abstract class Entity implements ISprite {

    private Vector2 pos;
    private Vector2  vel;
    private Vector2 acc;
    int health;
    private EDirection direction;
    private int size = Config.SPRITE_SIZE * 3;
    private Rect rect;

    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     * @param health
     */

    public Entity(int x, int y, double vel, int health){
        this.direction = EDirection.NOT_MOVING; // Default value
        this.pos = new Vector2(x, y);
        this.vel = new Vector2(vel, vel);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, size, size);
        this.health = health;
    }
    /**
     * @param direction, updated direction.
     * Used to update direction of entity.
     */
    public void setDirection(EDirection direction) {
        this.direction = direction;
    }

    /**
     * @return current direction.
     * Used to get the current direction of entity.
     */
    public EDirection getDirection() {
        return direction;
    }

    /**
     * Updates current position of entity on game screen.
     */
    public void updatePos(){
        if(!(direction == EDirection.NOT_MOVING)){
            switch (direction){
                case UP -> pos.y -= vel.y;
                case LEFT -> pos.x -= vel.x;
                case DOWN -> pos.y += vel.y;
                case RIGHT -> pos.x += vel.x;
            }
        }
    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    @Override
    public Rect getRect() {
        return new Rect(this.rect);
    }

    /**
     * Method used to update the entites' position and state.
     * Updates the entites' location by adding its coordinate with its velocity
     */
    @Override
    public void update() {
        updatePos();
    }

    /**
     * @return size of entity
     */
    public int getSize() {
        return size;
    }
}
