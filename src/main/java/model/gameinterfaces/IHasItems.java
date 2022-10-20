package model.gameinterfaces;

import model.gameobjects.ItemSpawner.IItem;

import java.util.List;

/**
 * Interface for reaching a list of items
 */
public interface IHasItems {
    List<IItem> getItems();
}
