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

    private Vector2 vel;

    private Vector2 acc;
    private int health;
    private EDirection direction;
    private EDirection lastDirection;
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
        this.lastDirection = direction;
        this.pos = new Vector2(x, y);
        this.vel = new Vector2(vel, vel);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, size, size);
        this.health = health;
    }

    public Vector2 getVel() {
        return vel;
    }

    public double getVelX(){
        return vel.getX();
    }

    public void setVelX(double velX){
        vel.setX(velX);
    }
    public void setPosX(double posX) {
        pos.setX(posX);
    }
    public double getPosX(){
        return pos.getX();
    }
    public void setAccX(double accX){
        acc.setX(accX);
    }
    public double getAccX(){
        return acc.getX();
    }
    public double getVelY(){
        return vel.getY();
    }

    public void setVelY(double velY){
        vel.setY(velY);
    }
    public void setPosY(double posY) {
        pos.setY(posY);
    }
    public double getPosY(){
        return pos.getY();
    }
    public void setAccY(double accY){
        acc.setY(accY);
    }
    public double getAccY(){
        return acc.getY();
    }

    public Vector2 getAcc() {
        return acc;
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }



    /**
     * @param direction, updated direction.
     * Used to update direction of entity.
     */
    public void setDirection(EDirection direction) {
        lastDirection = this.direction;
        this.direction = direction;
    }

    /**
     * @return current direction.
     * Used to get the current direction of entity.
     */
    public EDirection getDirection() {
        return direction;
    }
    public EDirection getLastDirection() {
        return lastDirection;
    }

    /**
     * Updates current position of entity on game screen.
     */
    public void updatePos(){
        if(!(direction == EDirection.NOT_MOVING)){
            switch (direction){
                case UP -> pos.setY(pos.getY() - vel.getY());
                case LEFT -> pos.setX(pos.getX() - vel.getX());
                case DOWN -> pos.setY(pos.getY() + vel.getY());
                case RIGHT -> pos.setX(pos.getX() + vel.getX());
            }
        }
    }

    public int getHealth(){
        return health;
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
    public Rect getRect() {
        return new Rect(this.rect);
    }

    /**
     * Method used to update the entites' position and state.
     * Updates the entites' location by adding its coordinate with its velocity
     */
    @Override
    public void update() {

    }

    /**
     * @return size of entity
     */
    public int getSize() {
        return size;
    }
}
