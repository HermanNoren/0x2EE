package sprites;

import helperclasses.Rect;
import helperclasses.Vector2;

import java.awt.*;

public class ShopSprite implements Sprite{
    public final static int x_position = 0;
    public final static int y_position = 0;
    private Rect shopAreaSize;
    private Vector2 Position;


    public final static int sizeLength = 25;

    public ShopSprite(int x_size, int y_size, int x_position, int y_position) {
        shopAreaSize = new Rect(100, 100,x_size , y_size);
        Position = new Vector2(x_position, y_position);
    }

    /**
     States the position of the shop on the map, one size as it has the same length as height.
     */
    public void openShopPanel(){

    }

    @Override
    public int getWidth() {
        return getRect().getWidth();
    }

    @Override
    public int getHeight() {
        return getRect().getHeight();
    }

    @Override
    public Vector2 getPos() {
        return this.Position;
    }

    @Override
    public Rect getRect() {
        return this.shopAreaSize;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.fillRect( x_position, y_position, sizeLength, sizeLength);
    }
}
