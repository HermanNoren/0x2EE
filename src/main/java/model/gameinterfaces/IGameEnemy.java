package model.gameinterfaces;

public interface IGameEnemy extends IHasEnemies, IHasProjectiles, IHasItems, ICreateGame, ICanPause, IHasHighscore, IShoppable, IHasPlayer, IHasGameMap, ICanSpawnEnemy {
    void update(double dt);
}
