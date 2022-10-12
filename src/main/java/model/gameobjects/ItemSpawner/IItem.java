package model.gameobjects.ItemSpawner;

import model.gameobjects.IGameObject;
import model.gameobjects.Player;

public interface IItem extends IGameObject {

    String getType();
    void consume(Player player);
}
