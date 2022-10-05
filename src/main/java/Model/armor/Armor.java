package Model.armor;

public class Armor implements IArmor {

    public Armor(){

    }

    public double armorLevel = 0;

    /**
     * @param damageTaken reduces damage
     * @return
     */
    @Override
    public double damageReduction(int damageTaken) {
        double reduction = armorLevel/100;
        return damageTaken * reduction;
    }

    /**
     * Used to increase armorLevel attribute.
     */
    @Override
    public void upgradeArmor() {
        armorLevel++;
        //Upgrades armorLevel
    }
}
