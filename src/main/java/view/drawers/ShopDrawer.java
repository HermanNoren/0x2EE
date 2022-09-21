package view.drawers;

import sprites.theShop.ShopSprite;
import view.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopDrawer implements IDrawer{
    Camera camera;
    private BufferedImage frame1;

    private ShopSprite shopObject;

    public ShopDrawer(ShopSprite shopObject, Camera camera){
        this.shopObject = shopObject;
        this.camera = camera;
        initShopImages();
    }

    private void initShopImages(){
        try{
             frame1 = setImage("imgs/shopPiskel_.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }

    private BufferedImage setImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    @Override
    public void draw(Graphics2D g2) {
        ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(shopObject.getPos(), 128, 128, camera);
        g2.drawImage(frame1, drawInformation.get(0),drawInformation.get(1),drawInformation.get(2),drawInformation.get(3), null);
    }

}
