
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

    public boolean isInteractable = false;

    private boolean upPressed, downPressed, leftPressed, rightPressed;
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
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        score = 0;
        money = 0;
        setHealth(1000);
        setMaxHp(1000);
        setVelX(0.5);
        setVelY(0.5);
    }
    public static Player createPlayer(Game game, Random rand){
        int xPos = (int) game.getGameMap().getPassableTerrains().get(rand.nextInt(game.getGameMap().getPassableTerrains().size()-1)).getPos().x;
        int yPos = (int) game.getGameMap().getPassableTerrains().get(rand.nextInt(game.getGameMap().getPassableTerrains().size()-1)).getPos().y;
        return new Player(xPos*48, yPos*48, game);
    }

    public void shoot(List<Projectile> projectiles) {
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
    public boolean isPassable() {
        return false;
    }

    @Override
    public void update() {

    }

    public void moveX(double speed) {
        acc.x = 0;
        if (getDirection() == EDirection.RIGHT) {
            acc.x = speed;
        }
        if (getDirection() == EDirection.LEFT) {
            acc.x = -speed;
        }
        acc.x += vel.x * -0.1;
        vel.x += acc.x;
        pos.x += vel.x + 0.5 * acc.x;

    }

    public void moveY(double speed) {
        acc.y = 0;

        if (getDirection() == EDirection.DOWN) {
            acc.y = speed;
        }

        if (getDirection() == EDirection.UP) {
            acc.y = -speed;
        }

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
