package view.drawers;

import view.ImageHandler;
import view.buttons.GameButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class used to draw the in Shop view
 * @author Gustav Gille
 */
public class InShopDrawer implements IDrawer{
    private final List<GameButton> buttons;

    private static BufferedImage weaponActive, weaponInactive, armorActive, armorInactive;

    /**
     * Takes the list of the picture buttons and an ImageHandler which initializes the pictures below.
     * @param buttons The list of buttons regarding the pictures.
     */
    public InShopDrawer(List<GameButton> buttons) {
        this.buttons = buttons;
        initShopImages();
    }

    /**
     * All the 4 parts of the pictures being initiated (see that they exist). They will represent their selected picture in case they
     * are selected, otherwise they will be idle. Logic in Draw.
     */
    private void initShopImages(){
        try{
            weaponActive = ImageHandler.getImage("imgs/shop/weapon/weaponActive.png");
            weaponInactive = ImageHandler.getImage("imgs/shop/weapon/weaponInactive.png");
            armorActive = ImageHandler.getImage("imgs/shop/armor/armorActive.png");
            armorInactive = ImageHandler.getImage("imgs/shop/armor/armorInactive.png");
        }
        catch (Exception errorMessage){
           errorMessage.printStackTrace();
        }
    }

    /**
     * Draws the picture representation. Loops over the two buttons and if the button is selected,
     * it will go through the if-statement which repaints it as selected and changes the border's
     * color from gray to green. The 4 used in the draw rect is used to give the proper width to
     * the box.
     * @param g2 See Graphics2D java documentation.
     */
    @Override
    public void draw(Graphics2D g2) {
        List<BufferedImage> activePictures = new ArrayList<>(Arrays.asList(weaponActive, armorActive));
        List<BufferedImage> inactivePictures  = new ArrayList<>(Arrays.asList(weaponInactive, armorInactive));
        for(GameButton button : buttons) {
            int currentXPosition = (int) button.getPos().getX();
            int currentYPosition = (int) button.getPos().getY();
            int currentButtonIteration = buttons.indexOf(button);

            BufferedImage currentPicture = activePictures.get(currentButtonIteration);
            int currentPictureWidth = currentPicture.getWidth();
            int currentPictureHeight = currentPicture.getHeight();

            g2.setStroke(new BasicStroke(8));
            int pictureScaling = 3;
            int borderWidth = 4;
            g2.drawImage( inactivePictures.get(currentButtonIteration),currentXPosition, currentYPosition, currentPictureWidth * pictureScaling,currentPictureHeight * pictureScaling, null);
            g2.setColor(Color.gray);
            g2.drawRect(currentXPosition, currentYPosition, currentPictureWidth * pictureScaling * borderWidth,currentPictureHeight * pictureScaling); //Draws border surrounding weapon and armor
                if(button.getIsSelected()){
                    g2.setColor(Color.green); // draw border green and button selected
                    g2.drawImage(currentPicture, currentXPosition, currentYPosition, currentPictureWidth * pictureScaling,currentPictureHeight * pictureScaling, null);
                    g2.drawRect(currentXPosition, currentYPosition, currentPictureWidth * pictureScaling * borderWidth,currentPictureHeight * pictureScaling); //Draws border surrounding weapon and armor
                }


        }
    }
}
