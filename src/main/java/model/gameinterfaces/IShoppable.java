package model.gameinterfaces;

import model.gameobjects.Shop;
import model.helperclasses.TransactionHandler;

public interface IShoppable{
    Shop getShop();
    TransactionHandler getShopTransaction();
}
