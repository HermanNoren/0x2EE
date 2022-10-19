package model.gameobjects.ItemSpawner;

import model.gameobjects.IGameObject;
import model.gameobjects.Player;

/**
 * Interface every spawnable item should implement
 */
public interface IItem extends IGameObject {
    String getType();
    void consume(Player player);
}
