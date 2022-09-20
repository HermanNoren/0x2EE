package sprites;

import equipment.Armor;
import controllers.EDirection;
import helperclasses.Vector2;
import weapons.Weapon;

/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements ISprite {
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

    public void shoot() {
        weapon.shoot();
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
        acc.x = 0;

        if (getDirection() == EDirection.RIGHT) { setAccX(0.1); }
        if (getDirection() == EDirection.LEFT) { setAccX(-0.1); }

        acc.x += vel.x * -0.1;
        vel.x += acc.x;
        pos.x += vel.x + 0.5 * acc.x;

        acc.y = 0;

        if (getDirection() == EDirection.DOWN) { setAccY(0.1);  }
        if (getDirection() == EDirection.UP) { setAccY(-0.1); }

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
