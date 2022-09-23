
package model.gameobjects;


import model.armor.Armor;
import controllers.EDirection;
import model.helperclasses.Vector2;
import model.weapons.Weapon;

import java.util.ArrayList;

/**
 * The player, more implementation to come.
 */

public class Player extends Entity implements IGameObject, IFocusableObject {

    private int score;
    private int money;
    protected Weapon weapon;
    protected Armor armor;
    boolean isDamageTaken;

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * @param health, starting health
     * Player constructor, used to create an instance of player.
     */
    public Player(int x, int y, double vel, int health){
        super(x, y, vel, health);
        this.armor = new Armor();
        this.weapon = new Weapon(10, 10);
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        score = 0;
        money = 0;
    }

    public void shoot(ArrayList<Projectile> projectiles) {
        EDirection dir;
        if (getDirection() == EDirection.NOT_MOVING) { dir = getLastDirection(); }
        else { dir = getDirection(); }
        weapon.shoot(getCenter(), dir, projectiles);
    }

    public void setUpPressed(boolean value) {
        upPressed = value;
    }

    public void setDownPressed(boolean value) {
        downPressed = value;
    }

    public void setRightPressed(boolean value) {
        rightPressed = value;
    }

    public void setLeftPressed(boolean value) {
        leftPressed = value;
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.x + (double) (getWidth() / 2);
        double y = pos.y + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public void update() {

        setAccX(0);
        if (getDirection() == EDirection.RIGHT) { setAccX(0.1); }
        if (getDirection() == EDirection.LEFT) { setAccX(-0.1); }

        setAccX(getAccX() + getVelX() * -0.1);
        setVelX(getVelX() + getAccX());
        setPosX(getPosX() + getVelX() + 0.5*getAccX());

        setAccY(0);
        if (getDirection() == EDirection.DOWN) { setAccY(0.1);  }
        if (getDirection() == EDirection.UP) { setAccY(-0.1); }

        setAccY(getAccY() + getVelY()*-0.1);
        setVelY(getVelY()+getAccY());

        setPosY(getPosY() + getVelY() + 0.5*getAccY());


        moveX();
        moveY();
    }

    public void moveX() {
        acc.x = 0;

        if (getDirection() == EDirection.RIGHT) { acc.x = 0.1; }
        if (getDirection() == EDirection.LEFT) { acc.x = -0.1; }

        acc.x += vel.x * -0.1;
        vel.x += acc.x;
        pos.x += vel.x + 0.5 * acc.x;
    }

    public void moveY() {
        acc.y = 0;

        if (getDirection() == EDirection.DOWN) { acc.y = 0.1;  }
        if (getDirection() == EDirection.UP) { acc.y = -0.1; }

        acc.y += vel.y * -0.1;
        vel.y += acc.y;
        pos.y += vel.y + 0.5 * acc.y;
    }

    /**
     * add the weapon object into the attack.
     */
    public int damageDelt() {
        return weapon.damage;
    }

    public void damageTaken(int damage) {
        setHealth(getHealth() - damage);
    }

    public boolean isDamageTaken(){

        return isDamageTaken;
    }

    /**
     * @return score acquired during game
     */
    public int getScore(){
        return score;
    }

    /**
     * @return currency acquired during game
     */
    public int getMoney(){
        return money;
    }
}
