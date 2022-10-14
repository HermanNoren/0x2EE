package model.gameinterfaces;

import model.gameobjects.ItemSpawner.IItem;

import java.util.List;

public interface IItemsGettable {
    List<IItem> getItems();
}
