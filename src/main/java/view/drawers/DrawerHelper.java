package view.drawers;

import model.helperclasses.Vector2;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

/**
 * The DrawerHelper class is made to aid the representation of the sprite relative to the camera-view.
 */
public class DrawerHelper implements IDrawer {

    public static ArrayList<Integer> calculateDrawingInformation(Vector2 pos, int width, int height) {
        ArrayList<Integer> drawInformation = new ArrayList<>();
        Camera camera = Camera.getInstance();

        double x = (pos.x - camera.getOffset().x) * camera.getZoomMultiplier();
        double y = (pos.y - camera.getOffset().y) * camera.getZoomMultiplier();
        double newWidth = width * camera.getZoomMultiplier();
        double newHeight = height * camera.getZoomMultiplier();

        drawInformation.add((int) Math.floor(x));
        drawInformation.add((int) Math.floor(y));
        drawInformation.add((int) Math.ceil(newWidth));
        drawInformation.add((int) Math.ceil(newHeight));

        return drawInformation;
        //Returnera inte listan utan rita direkt!
    }


    @Override
    public void draw(Graphics2D g2) {

    }
}
