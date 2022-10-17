package model.gameinterfaces;

import model.gameobjects.ItemSpawner.IItem;

import java.util.List;

public interface IHasItems {
    List<IItem> getItems();
}
