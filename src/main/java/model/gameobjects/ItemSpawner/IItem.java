package model.gameobjects.ItemSpawner;

import model.gameobjects.IGameObject;
import model.gameobjects.Player;

public interface IItem extends IGameObject {

    void consume(Player player);
}
