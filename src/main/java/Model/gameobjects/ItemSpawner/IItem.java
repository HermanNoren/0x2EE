package Model.gameobjects.ItemSpawner;

import Model.gameobjects.IGameObject;
import Model.gameobjects.Player;

public interface IItem extends IGameObject {

    void consume(Player player);
}
