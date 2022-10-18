package model.upgradables.armor;
import model.upgradables.Upgradable;

/**
 * Class which represents the player's armor.
 * Is delegated to the player.
 */

public class Armor extends Upgradable implements IArmor {
    private final double armorReductionConstant  = 0.08;

    public Armor(){
        super(1, 40);
    }

    /**
     * Method to determine how much damage reduction the player can receive,
     * determined by the player's level and an armor constant.
     * @param damageTaken the amount of damage taken
     * @return the reduced amount of damage taken
     */
    @Override
    public double damageReduction(int damageTaken) {
        return damageTaken - (super.getCurrentLevel() * armorReductionConstant);
    }

}
