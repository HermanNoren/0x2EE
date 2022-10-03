
package model.gameobjects;


import model.Game;
import model.armor.Armor;
import controllers.EDirection;
import model.helperclasses.Vector2;
import model.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player, more implementation to come.
 */

public class Player extends Entity implements IGameObject, IFocusableObject {

    private int score;
    private int money;
    protected Weapon weapon;
    protected Armor armor;
    boolean isDamageTaken;
    private double acceleration;
    public boolean isInteractable = false;

    /**
     * @param x,   starting x-position
     * @param y,   starting y-position
     *             Player constructor, used to create an instance of player.
     * @param game
     */
    public Player(int x, int y, Game game){
        super(x, y);
        this.armor = new Armor();
        this.weapon = new Weapon(10, 10);
        score = 0;
        money = 0;
        acceleration = 0.2;
        setHealth(1000);
        setMaxHp(1000);
    }

    public void shoot(List<Projectile> projectiles) {
        EDirection dir;
        if (getDirection() == EDirection.NOT_MOVING) { dir = getLastDirection(); }
        else { dir = getDirection(); }
        weapon.shoot(getCenter(), dir, projectiles);
    }

    @Override
    public Vector2 getCenter() {
        double x = pos.x + (double) (getWidth() / 2);
        double y = pos.y + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public void update(double dt) {
        moveX(dt);
        moveY(dt);
    }

    public void moveX(double dt) {
        acc.x = 0;

        if (getDirection() == EDirection.RIGHT) { acc.x = acceleration; }
        if (getDirection() == EDirection.LEFT) { acc.x = -acceleration; }

        acc.x += vel.x * -0.12;
        vel.x += acc.x * dt;
        pos.x += vel.x * dt + (acc.x * 0.5) * (dt * dt);
    }

    public void moveY(double dt) {
        acc.y = 0;

        if (getDirection() == EDirection.DOWN) { acc.y = acceleration;  }
        if (getDirection() == EDirection.UP) { acc.y = -acceleration; }

        acc.y += vel.y * -0.12;
        vel.y += acc.y * dt;
        pos.y += vel.y * dt + (acc.y * 0.5) * (dt * dt);
    }

    public void stopCurrentMovement() {
        vel = new Vector2(0, 0);
        acc = new Vector2(0, 0);
    }

    /**
     * add the weapon object into the attack.
     */
    public int damageDelt() {
        return weapon.damage;
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
}
