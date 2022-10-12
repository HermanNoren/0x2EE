package view.drawers;


import model.gameobjects.Shop;
import model.helperclasses.ImageHandler;
import model.helperclasses.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopDrawer implements IDrawer{
    private static BufferedImage topLeft, topRight, bottomLeft, bottomRight;
    private final Shop shop;

    private final double xCoordinate;
    private final double yCoordinate;
    private final double shopWidth;
    private final double shopHeight;
    private final int amountOfPicturesOffset = (int) Math.sqrt(4);

    private final ImageHandler imageHandler;
    public ShopDrawer(Shop shop){
        this.shop = shop;
        xCoordinate = shop.getPos().getX();
        yCoordinate = shop.getPos().getY();
        shopWidth = shop.getWidth();
        shopHeight = shop.getHeight();
        this.imageHandler = new ImageHandler();
        initShopImages();
    }


    /**
     * Initiate the 4 pictures of the shop
     */
    private void initShopImages(){
        try{
           topLeft = imageHandler.getImage("imgs/shop/ShoppingPNGS/shoptopleft.png");
           topRight = imageHandler.getImage("imgs/shop/ShoppingPNGS/shoptopright.png");
           bottomLeft = imageHandler.getImage("imgs/shop/ShoppingPNGS/shopbottomleft.png");
           bottomRight = imageHandler.getImage("imgs/shop/ShoppingPNGS/shopbottomright.png");
        }
        catch (Exception errorMessage){
            System.out.println(errorMessage.getMessage());
        }
    }


    /**
     * To make the view depend on the model and not the model on the view,
     * the view has been adapted to fit the model.
     * This implements some calculating in the view as to correct the offset.
     * @param image The image being processed, could be a switch case also but
     *              if equals is a boolean expression.
     * @return The correct image, throw error if not found
     *
     */
    public Vector2 pictureOffsetCorrector(BufferedImage image){
    try{
            if (topLeft.equals(image))
                return new Vector2(shop.getPos());

            else if (topRight.equals(image))
                return new Vector2(xCoordinate + (shopWidth/amountOfPicturesOffset), yCoordinate);

            else if (bottomRight.equals(image))
                return new Vector2(xCoordinate + (shopWidth/amountOfPicturesOffset), yCoordinate+  shopHeight/amountOfPicturesOffset);

            else if (bottomLeft.equals(image))
                return new Vector2(xCoordinate, yCoordinate  + shopHeight/amountOfPicturesOffset);

        }   catch (Exception e){
                System.out.println(e.getMessage());
            }
    return null;
    }

    /**
     * Iterates through the list consisting of the pictures. They are then passed through the
     * PictureOffsetCorrector, which aligns the pictures correctly. See the DrawHelper method
     * calculateDrawingInformation to see how the is used to align the picture representation
     * with the camera.
     * @param g2 The interface for which rendering 2D logic is done through,
     *           see more at the Java Graphics2D documentation.
     */
    @Override
    public void draw(Graphics2D g2) {
        List<BufferedImage> pictures = new ArrayList<>(Arrays.asList(topLeft, topRight, bottomLeft, bottomRight));
        for(BufferedImage image : pictures) {
            List<Integer> currentPictureInformationRepresentation = DrawerHelper.calculateDrawingInformation(pictureOffsetCorrector(image), (int) (shopWidth/2), (int) (shopHeight/2));
            g2.drawImage(image, currentPictureInformationRepresentation.get(0), currentPictureInformationRepresentation.get(1), currentPictureInformationRepresentation.get(2), currentPictureInformationRepresentation.get(3), null);
        }
    }
}
