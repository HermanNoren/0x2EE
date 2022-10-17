package model.gameinterfaces;

public interface IGame extends IHasEnemies, IProjectilesGettable, IHasItems, ICreateGame, ICanPause, IHasHighscore, IShoppable, IHasPlayer, IHasGameMap, ICanSpawn {
    void update(double dt);
}
