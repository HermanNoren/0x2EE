package model.upgradables.armor;
import model.upgradables.Upgradable;

/**
 * The Armor representation, used to determine the armor reduction constant and acts as the player's armor object
 * when initiated.
 * @Author Gustav Gille
 */

public class Armor extends Upgradable implements IArmor {
    private final double armorReductionConstant  = 0.01;

    public Armor(){
        super(1, 2);
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
