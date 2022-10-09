package controllers;

import model.ShopTransaction;
import view.buttons.GameButton;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class ShopController implements KeyListener {
    private ShopTransaction shopTransaction;
    private List<GameButton> buttons;

    public ShopController(ShopTransaction shopTransaction, List<GameButton> buttons){
        this.shopTransaction = shopTransaction;
        this.buttons = buttons;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case (KeyEvent.VK_ENTER) ->{
                for(GameButton button : buttons){
                    if(button.getIsSelected()){
                        shopTransaction.upgrade(button.getButtonText());
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
