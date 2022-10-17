package model.upgradables.armor;

/**
 * Contains method that determines the reduced damage taken, damageReduction.
 */
public interface IArmor {
    /**
     * Implemented method which contains the functionality to reduce the amount of
     * damage taken.
     */
    double damageReduction(int damageTaken);
}