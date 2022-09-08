package sprites;

import armor.Armor;
import weapons.Pistol;

/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements Sprite, MovableSprite{
    private int score;
    private int money;
    protected Pistol pistol;

    protected Armor armor;

    /**
     * @param x, starting x-position
     * @param y, starting y-position
     * @param health, starting health
     * Player constructor, used to create an instance of player.
     */
    public Player(int x, int y, int health){
        super(x, y, health);
        score = 0;
        money = 0;
    }

    /**
     * add the weapon object into the attack.
     */
    public int damageDelt() {
        return this.pistol.damage;
    }

    public void damageTaken(int damage) {

    }

    /**
     * @return score acquired during game
     */
    public int getScore(){
        return score;
    }

    /**
     * @return player's health
     */
    public int getHealth(){
        return health;
    }

    /**
     * @return currency acquired during game
     */
    public int getMoney(){
        return money;
    }
}
