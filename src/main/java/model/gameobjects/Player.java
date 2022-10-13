
package model.gameobjects;

import model.armor.Armor;
import controllers.EDirection;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;
import model.weapons.Weapon;

import java.util.List;

/**
 * The player, more implementation to come.
 */

public class Player extends Entity implements IPlayer, IFocusableObject {
    private int score;
    private int money;
    private Weapon weapon;
    private Armor armor;
    private double moveAcc;

    public boolean isOnShop = false;
    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * Player constructor, used to create an instance of player.
     */
    public Player(int x, int y, Terrain[][] coordinates){
        super(x, y, coordinates);
        this.armor = new Armor();
        this.weapon = new Weapon(10, 10);
        setMaxHp(1000);
        setHealth(1000);
        moveAcc = 0.3;
        score = 0;
        money = 0;
    }

    public void shoot(IProjectileAddable addable) {
        EDirection dir;

        if (getDirection() == EDirection.NOT_MOVING) {
            dir = getLastDirection();
        }

        else {
            dir = getDirection();
        }
        weapon.shoot(getCenter(), dir, addable);
    }

    public void stopCurrentXMovement() {
        setVelX(0);
        setAccX(0);
    }

    public void stopCurrentYMovement() {
        setVelY(0);
        setAccY(0);
    }

    public void stopAllCurrentMovement(){
        stopCurrentXMovement();
        stopCurrentYMovement();
    }

    public void moveX(double dt) {
        setAccX(0);
        if (getDirection() == EDirection.RIGHT) {
            setAccX(moveAcc);
        }
        if (getDirection() == EDirection.LEFT) {
            setAccX(-moveAcc);
        }

        setAccX(getAccX() + getVelX() * -0.12);
        setVelX(getVelX() + getAccX() * dt);
        setPosX(getPosX() + getVelX() * dt + 0.5 * getAccX() * (dt * dt));

    }

    public void moveY(double dt) {
        setAccY(0);

        if (getDirection() == EDirection.DOWN) {
            setAccY(moveAcc);
        }

        if (getDirection() == EDirection.UP) {
            setAccY(-moveAcc);
        }

        setAccY(getAccY() + getVelY() * -0.12);
        setVelY(getVelY() + getAccY() * dt);
        setPosY(getPosY() + getVelY() * dt + 0.5 * getAccY() * (dt * dt));
    }
    /**
     * @return score acquired during game
     */
    public int getScore(){
        return score;
    }
    /**
     * Adds score to total
     * @param score to add
     */
    public void addScore(int score){
        this.score += score;
    }
    /**
     * @return currency acquired during game
     */
    public int getMoney(){
        return money;
    }

    public Weapon getWeapon(){
        return this.weapon;
    }

    public Armor getArmor(){
        return this.armor;
    }
    public void setMoney( int newAmount) {
        money = newAmount;
    }
    @Override
    public void damageTaken(int damage){
       setHealth((int) (getHealth() - armor.damageReduction(damage)));
    }

    public void addMoney(int amount){
        this.money += amount;
    }
}
