package sprites;

import armor.Armor;
import controllers.Direction;
import org.imgscalr.Scalr;
import weapons.Pistol;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;


/**
 * The player, more implementation to come.
 */
public class Player extends Entity implements Sprite, MovableSprite{
    private int score;
    private int money;
    protected Pistol pistol;

    protected Armor armor;

    public Player(int x, int y, int health) throws IOException {
        super(x, y, health);
        score = 0;
        money = 0;
    }

    /**
     * add the weapon object into the attack.
     */
    @Override
    public int damageDelt() {
        return this.pistol.damage;
    }

    @Override
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
