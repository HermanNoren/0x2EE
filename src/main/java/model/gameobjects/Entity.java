package model.gameobjects;

import config.Config;
import controllers.EDirection;
import model.Game;
import model.helperclasses.Rect;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;

/**
 * The IEnemy class contains logic to represent the sprite,
 * for example by containing information regarding its position
 * and health the program can determine if an entity is in range
 * to take damage thus if reduces its health.
 */

public abstract class Entity implements IGameObject {
    private Vector2 pos;
    private Vector2 vel;
    private Vector2 acc;
    private int health;
    private int maxHp;
    private EDirection direction;
    private EDirection lastDirection;
    private int size = Config.SPRITE_SIZE*3;
    private Rect rect;
    private Terrain currentLocation;
    private Game game;

    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     */

    public Entity(int x, int y, Game game){
        this.game = game;
        this.direction = EDirection.NOT_MOVING; // Default value
        this.lastDirection = direction;
        this.pos = new Vector2(x, y);
        this.acc = new Vector2(0, 0);
        this.rect = new Rect(x, y, size, size);
        this.vel = new Vector2(0,0);
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
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
    public void setCurrentLocation(){

    }

    public Terrain getMapLocation(){

        int posX = (int) (getPosX() + getWidth()/2)/48;
        int posY = (int) (getPosY() + getHeight()/2)/48;
        currentLocation = game.getGameMap().getGameMapCoordinates()[posX][posY];
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

    public void setHealth(int value) {
        if (value < 0) {
            health = 0;
        }else health = Math.min(value, maxHp);
    }
    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public int getHealth(){
        return health;
    }

    public double getMaxHp() {
        return maxHp;
    }
    @Override
    public int getWidth() {
        return size;
    }

    @Override
    public int getHeight() {
        return size;
    }


    public void setVel(Vector2 vel) {
        this.vel = vel;
    }

    public void setAcc(Vector2 acc) {
        this.acc = acc;
    }

    public void damageTaken(int damage) {
        setHealth(getHealth() - damage);
    }

    /**
     * @return size of entity
     */
    public int getSize() {
        return size;
    }
}


