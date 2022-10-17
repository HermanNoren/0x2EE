package model.gameinterfaces;

public interface IGame extends IHasEnemies, IHasProjectiles, IHasItems, ICreateGame, ICanPause, IHasHighscore, IShoppable, IHasPlayer, IHasGameMap, ICanSpawnEnemy {
    void update(double dt);
}
