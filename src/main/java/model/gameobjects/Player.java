
package model.gameobjects;

import model.gameinterfaces.IHasProjectiles;
import model.upgradables.armor.Armor;

import model.mapclasses.Tile;
import model.upgradables.weapon.Weapon;

/**
 * Class used to create player within the game. A player has a weapon and armor which acts as compositions.
 * Also has movement logic.
 * @Author
 */
public class Player extends Entity implements IPlayer, IFocusableObject, IHasUpgradable {
    private int score;
    private int money;
    private final Weapon weapon;
    private final Armor armor;
    private final double moveAcc;
    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * Player constructor, used to create an instance of player.
     */
    public Player(int x, int y, Tile[][] coordinates){
        super(x, y, coordinates);
        this.armor = new Armor();
        this.weapon = new Weapon();
        setMaxHp(1000);
        setHealth(1000);
        moveAcc = 0.3;
        score = 0;
        money = 10;
    }

    /**
     * {@inheritDoc}
     * @param addable {@inheritDoc}
     */
    @Override
    public void shoot(IHasProjectiles addable) {
        EDirection dir;

        if (getDirection() == EDirection.NOT_MOVING) {
            dir = getLastDirection();
        }

        else {
            dir = getDirection();
        }
        weapon.shoot(getCenter(), dir, addable);
    }

    @Override
    public void stopCurrentXMovement() {
        setVelX(0);
        setAccX(0);
    }

    @Override
    public void stopCurrentYMovement() {
        setVelY(0);
        setAccY(0);
    }

    @Override
    public void stopAllCurrentMovement(){
        stopCurrentXMovement();
        stopCurrentYMovement();
    }

    @Override
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

    @Override
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
    @Override
    public int getScore(){
        return score;
    }
    /**
     * Adds score to total
     * @param score to add
     */
    @Override
    public void addScore(int score){
        this.score += score;
    }
    /**
     * @return currency acquired during game
     */
    @Override
    public int getMoney(){
        return money;
    }
    @Override
    public void setMoney( int newAmount) {
        money = newAmount;
    }
    @Override
    public Weapon getWeapon(){
        return this.weapon;
    }
    @Override
    public Armor getArmor(){
        return this.armor;
    }
    @Override
    public void damageTaken(int damage){
        if(damage > 0) setHealth((int) (getHealth() - armor.damageReduction(damage)));
    }
    @Override
    public void addMoney(int amount){
        this.money += amount;
    }
}
