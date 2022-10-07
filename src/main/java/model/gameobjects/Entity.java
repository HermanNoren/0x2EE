package model.gameobjects;

import config.Config;
import controllers.EDirection;
import model.helperclasses.Rect;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;

/**
 * The IEnemy class contains logic to represent the sprite,
 * for example by containing information regarding its position
 * and health the program can determine if an entity is in range
 * to take damage thus if reduces its health.
 */

public abstract class Entity implements IEntity, IGameObject {
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private int health;
    private int maxHp;
    private EDirection direction;
    private EDirection lastDirection;
    private int width = Config.ENTITY_WIDTH;
    private int height = Config.ENTITY_HEIGHT;
    private Rect rect;
    private Terrain currentLocation;
    private Terrain[][] coordinates;

    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     */

    public Entity(int x, int y, Terrain[][] coordinates){
        this.coordinates = coordinates;
        this.direction = EDirection.NOT_MOVING; // Default value
        this.lastDirection = direction;
        this.pos = new Vector2(x, y);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, width, height);
        this.vel = new Vector2(0,0);
    }

    @Override
    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
    @Override
    public double getVelX(){
        return vel.getX();
    }
    @Override
    public void setVelX(double velX){
        vel.setX(velX);
    }
    @Override
    public void setPosX(double posX) {
        pos.setX(posX);
    }
    @Override
    public double getPosX(){
        return pos.getX();
    }
    @Override
    public void setAccX(double accX){
        acc.setX(accX);
    }
    @Override
    public double getAccX(){
        return acc.getX();
    }
    @Override
    public double getVelY(){
        return vel.getY();
    }
    @Override
    public void setVelY(double velY){
        vel.setY(velY);
    }
    @Override
    public void setPosY(double posY) {
        pos.setY(posY);
    }
    @Override
    public double getPosY(){
        return pos.getY();
    }
    @Override
    public void setAccY(double accY){
        acc.setY(accY);
    }
    @Override
    public double getAccY(){
        return acc.getY();
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public Terrain getMapLocation(Terrain[][] coordinates){
        int posX = (int)getCenter().getX()/48;
        int posY = (int)getCenter().getY()/48;
        currentLocation = coordinates[posX][posY];
        return currentLocation;
    }

    @Override
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    /**
     * @param direction, updated direction.
     * Used to update direction of entity.
     */
    @Override
    public void setDirection(EDirection direction) {
        lastDirection = this.direction;
        this.direction = direction;
    }

    /**
     * @return current direction.
     * Used to get the current direction of entity.
     */
    @Override
    public EDirection getDirection() {
        return direction;
    }

    @Override
    public EDirection getLastDirection() {
        return lastDirection;
    }

    @Override
    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        }else{
            this.health = Math.min(health, maxHp);
        }
    }
    @Override
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }
    @Override
    public int getHealth(){
        return health;
    }
    @Override
    public double getMaxHp() {
        return maxHp;
    }
    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public void setVel(Vector2 vel) {
        this.vel = vel;
    }
    @Override
    public Vector2 getVel(){
        return new Vector2(vel);
    }
    @Override
    public Vector2 getAcc(){
        return new Vector2(acc);
    }
    @Override
    public void setAcc(Vector2 acc) {
        this.acc = acc;
    }
    @Override
    public void damageTaken(int damage) {
        setHealth(getHealth() - damage);
    }
}


