package view.drawers;

import model.gameobjects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Maybe an unnecessary class?? Thought to represent the view of the interact-functionality.
 */

public class InteractableDrawer implements IDrawer{
    private BufferedImage interactable, notInteractable, currentImage;

    Player player;

    public InteractableDrawer(Player player){
        this.player = player;
    }


    private void initShopImages(){
        try{
            interactable = setImage("imgs/InteractionView/Interactable.png");
            notInteractable = setImage("imgs/InteractionView/NotInteractable.png");
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
    private void setCurrentImage(){
        if(!player.isInteractable){
            currentImage = interactable;
        }else{
            currentImage = notInteractable;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(300, 200, 16, 16);


    }
}
