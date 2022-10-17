package model.gameinterfaces;
/**
 * Used to implement logic regarding the dynamical spawning of enemies.
 */
public interface ICanSpawnEnemy {
    /**
     * Called from game to add an enemy where the counter is
     * used to determine how often said enemy will be spawned.
     * @param counter How often an enemy will be spawned, used with modulo.
     */
    void spawnEnemy(int counter);
}
