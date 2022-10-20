package model.gameinterfaces;

/**
 * Interface a game should implement
 */
public interface IGame extends IHasEnemies, IHasProjectiles, IHasItems, ICreateGame, ICanPause, IHasHighscore, IShoppable, IHasPlayer, IHasGameMap, ICanSpawnEnemy, ISoundObservable {
    void update(double dt);
}
