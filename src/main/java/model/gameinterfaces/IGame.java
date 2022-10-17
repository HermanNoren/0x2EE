package model.gameinterfaces;

public interface IGame extends IEnemiesGettable, IProjectileAddable, IProjectilesGettable, IItemsGettable, INewGamable, IPausable, IHighscorable, IShoppable, IPlayerGettable, IGameMapGettable, IShootable, ISpawnable, IPlayerOnShop {
    void update(double dt);

}
