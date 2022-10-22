package model.gameinterfaces;

import model.gameobjects.Shop;

public interface IHasShop {
    Shop getShop();
    boolean isPlayerOnShop();
}
