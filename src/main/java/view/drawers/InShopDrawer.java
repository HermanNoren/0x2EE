package view.drawers;

import config.Config;
import model.helperclasses.ImageHandler;
import view.buttons.GameButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InShopDrawer implements IDrawer{

    private final ImageHandler imageHandler;
    private final List<GameButton> buttons;

    private final int pictureScaling = 3;
    private static BufferedImage weaponActive, weaponInactive, armorActive, armorInactive;

    //See which one is hovered
    public InShopDrawer(List<GameButton> buttons) {
        this.buttons = buttons;
        this.imageHandler = new ImageHandler();
        initShopImages();
    }

    private void initShopImages(){
        try{
            weaponActive = imageHandler.getImage("imgs/shop/weapon/weaponActive.png");
            weaponInactive = imageHandler.getImage("imgs/shop/weapon/weaponInactive.png");
            armorActive = imageHandler.getImage("imgs/shop/armor/armorActive.png");
            armorInactive = imageHandler.getImage("imgs/shop/armor/armorInactive.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }



    @Override
    public void draw(Graphics2D g2) {
        List<BufferedImage> activePictures = new ArrayList<>(Arrays.asList(weaponActive, armorActive));
        List<BufferedImage> inactivePictures  = new ArrayList<>(Arrays.asList(weaponInactive, armorInactive));
        for(GameButton button : buttons) {
            int currentButtonIteration = buttons.indexOf(button);
            BufferedImage currentPicture = activePictures.get(currentButtonIteration);
            g2.setStroke(new BasicStroke(8));
            g2.drawImage( inactivePictures.get(currentButtonIteration), (int)button.getPos().getX(), (int)button.getPos().getY(), currentPicture.getWidth() * pictureScaling ,currentPicture.getHeight() * pictureScaling, null);
            g2.setColor(Color.gray);
            g2.drawRect((int)button.getPos().getX(), (int)button.getPos().getY(), currentPicture.getWidth() * pictureScaling * 4,currentPicture.getHeight() * pictureScaling); //Draws border surrounding weapon and armor
                if(button.getIsSelected()){
                    g2.setColor(Color.green); // draw border green and button selected
                    g2.drawImage(currentPicture, (int)button.getPos().getX(), (int)button.getPos().getY(), currentPicture.getWidth() * pictureScaling ,currentPicture.getHeight() * pictureScaling, null);
                    g2.drawRect((int)button.getPos().getX(), (int)button.getPos().getY(), currentPicture.getWidth() * pictureScaling * 4,currentPicture.getHeight() * pictureScaling); //Draws border surrounding weapon and armor
                }


        }
    }
}
