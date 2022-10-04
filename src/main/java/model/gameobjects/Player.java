
package model.gameobjects;


import model.Game;
import model.armor.Armor;
import controllers.EDirection;
import model.helperclasses.Vector2;
import model.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The player, more implementation to come.
 */

public class Player extends Entity implements IGameObject, IFocusableObject {

    private int score;
    private int money;
    protected Weapon weapon;
    protected Armor armor;
    boolean isDamageTaken;

    private double moveAcc;

    public boolean isInteractable = false;
    private Game game;
    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * Player constructor, used to create an instance of player.
     */
    private Player(int x, int y, Game game){
        super(x, y, game);
        this.game = game;
        this.armor = new Armor();
        this.weapon = new Weapon(10, 10);
        setMaxHp(1000);
        setHealth(1000);
        moveAcc = 0.3;
        score = 0;
        money = 0;
    }
    public static Player createPlayer(Game game, Random rand){
        int xPos = (int) game.getGameMap().getPassableTerrains().get(rand.nextInt(game.getGameMap().getPassableTerrains().size())).getPos().getX();
        int yPos = (int) game.getGameMap().getPassableTerrains().get(rand.nextInt(game.getGameMap().getPassableTerrains().size())).getPos().getY();

        return new Player(xPos, yPos, game);
    }

    public void shoot(List<Projectile> projectiles) {
        EDirection dir;
        if (getDirection() == EDirection.NOT_MOVING) { dir = getLastDirection(); }
        else { dir = getDirection(); }
        weapon.shoot(getCenter(), dir, projectiles);
    }

    @Override
    public Vector2 getCenter() {
        double x = getPosX() + (double) (getWidth() / 2);
        double y = getPosY() + (double) (getHeight() / 2);
        return new Vector2(x, y);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    public void update(double dt) {

    }

    public void stopCurrentMovement(){
        setVel(new Vector2(0,0));
        setAcc(new Vector2(0,0));
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

    public void addMoney(int amount){
        this.money += amount;
    }
}
