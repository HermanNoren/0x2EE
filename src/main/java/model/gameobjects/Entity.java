package model.gameobjects;

import config.Config;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;
import model.mapclasses.Tile;

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
    private int size = Config.SPRITE_SIZE * 3;
    private Tile[][] coordinates;
    private Tile currentLocation;

    /**
     *
     * @param x represents the entities' x-coordinate
     * @param y represents the entities' y-coordinate
     */
    protected Entity(int x, int y, Tile[][] coordinates){
        this.coordinates = coordinates;
        this.direction = EDirection.NOT_MOVING; // Default value
        this.lastDirection = direction;
        this.pos = new Vector2(x, y);
        this.acc = new Vector2(0, 0);
        this.vel = new Vector2(0,0);
    }
    public Vector2 getPos() {
        return new Vector2(pos);
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }
    public Vector2 getVel(){
        return new Vector2(vel);
    }
    public void setVel(Vector2 vel) {
        this.vel = vel;

    }
    public Vector2 getAcc(){
        return new Vector2(acc);
    }

    public void setAcc(Vector2 acc) {
        this.acc = acc;
    }

    public void setPosY(double posY) {
        pos.setY(posY);
    }
    public double getPosY(){
        return pos.getY();
    }
    public void setPosX(double posX) {
        pos.setX(posX);
    }
    public double getPosX(){
        return pos.getX();
    }
    public double getVelX(){
        return vel.getX();
    }
    public void setVelX(double velX){
        vel.setX(velX);
    }
    public double getVelY(){
        return vel.getY();
    }
    public void setVelY(double velY){
        vel.setY(velY);
    }
    public void setAccX(double accX){
        acc.setX(accX);
    }
    public double getAccX(){
        return acc.getX();
    }
    public void setAccY(double accY){
        acc.setY(accY);
    }
    public double getAccY(){
        return acc.getY();
    }



    @Override
    public Vector2 getCenter() {
        double x = pos.getX() + (double) (getWidth() / 2);
        double y = pos.getY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }
    /**
     * @return current location based on the terrain grid of GameMap
     */
    public Tile getMapLocation(){
        int posX = (int)getCenter().getX()/48; // 48 is tilw size
        int posY = (int)getCenter().getY()/48;
        currentLocation = coordinates[posX][posY];
        return currentLocation;
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
     * Sets the health of the entity. Cannot be higher than maxHP.
     * @param value of new health
     */
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
    /**
     * Sets a new health according to how much damage is taken
     * @param damage taken
     */
    public void damageTaken(int damage) {
        setHealth(getHealth() - damage);
    }

}


