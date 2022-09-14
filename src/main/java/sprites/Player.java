package sprites;

import armor.Armor;
import controllers.EDirection;
import main.Game;
import weapons.Weapon;

/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements ISprite, IMovableSprite {
    private int score;
    private int money;
    private Weapon weapon;

    private Armor armor;
    private boolean isDamageTaken;
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
        score = 0;
        money = 0;
    }
    /**
     * add the weapon object into the attack.
     */
    public int damageDelt() {
        return weapon.damage;
    }


    public void damageTaken(int damage) {
        this.health -= armor.damageReduction(damage);
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
     * @return the player health
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
