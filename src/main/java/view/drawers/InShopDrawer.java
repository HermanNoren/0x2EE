package view.drawers;

import view.ImageHandler;
import view.buttons.GameButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InShopDrawer implements IDrawer{

    private final ImageHandler imageHandler;
    private final List<GameButton> buttons;

    private static BufferedImage weaponActive, weaponInactive, armorActive, armorInactive;

    /**
     * Takes the list of the picture buttons and an ImageHandler which initializes the pictures below.
     * @param buttons The list of buttons regarding the pictures.
     */
    public InShopDrawer(List<GameButton> buttons) {
        this.buttons = buttons;
        this.imageHandler = new ImageHandler();
        initShopImages();
    }

    /**
     * All the 4 parts of the pictures being initiated (see that they exist). They will represent their selected picture in case they
     * are selected, otherwise they will be idle. Logic in Draw.
     */

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
            g2.drawImage( inactivePictures.get(currentButtonIteration),currentXPosition, currentYPosition, currentPictureWidth * pictureScaling,currentPictureHeight * pictureScaling, null);
            g2.setColor(Color.gray);
            g2.drawRect(currentXPosition, currentYPosition, currentPictureWidth * pictureScaling * 4,currentPictureHeight * pictureScaling); //Draws border surrounding weapon and armor
                if(button.getIsSelected()){
                    g2.setColor(Color.green); // draw border green and button selected
                    g2.drawImage(currentPicture, currentXPosition, currentYPosition, currentPictureWidth * pictureScaling,currentPictureHeight * pictureScaling, null);
                    g2.drawRect(currentXPosition, currentYPosition, currentPictureWidth * pictureScaling * 4,currentPictureHeight * pictureScaling); //Draws border surrounding weapon and armor
                }


        }
    }
}
