package model.upgradables.weapon;

import model.gameinterfaces.IHasProjectiles;
import model.helperclasses.EDirection;
import model.helperclasses.Vector2;

/**
 * Contains method which are called regarding the weapon, shoot and reload.
 */
public interface IWeapon{
    /**
     * Used for creating bullets, position to see where the bullet is shot, direction
     * for which direction the bullet is fired in and addable adds a projectile to
     * the game with the position and direction.
     * @param pos The position of the projectile.
     * @param direction The direction of the projectile.
     * @param addable Adds the projectile with the previously mentioned attributes.
     */
    void shoot(Vector2 pos, EDirection direction, IHasProjectiles addable);

}
