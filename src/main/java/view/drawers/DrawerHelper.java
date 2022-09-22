package view.drawers;

import helperclasses.Vector2;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

/**
 * The DrawerHelper class is made to aid the representation of the sprite relative to the camera-view.
 */
public class DrawerHelper implements IDrawer {

    public static ArrayList<Integer> calculateDrawingInformation(Vector2 pos, int width, int height, Camera camera) {
        ArrayList<Integer> drawInformation = new ArrayList<>();

        double x = (pos.x - camera.getOffset().x) * camera.getZoomMultiplier();
        double y = (pos.y - camera.getOffset().y) * camera.getZoomMultiplier();
        double newWidth = width * camera.getZoomMultiplier();
        double newHeight = height * camera.getZoomMultiplier();

        drawInformation.add((int) Math.round(x));
        drawInformation.add((int) Math.round(y));
        drawInformation.add((int) Math.round(newWidth));
        drawInformation.add((int) Math.round(newHeight));

        return drawInformation;
        //Returnera inte listan utan rita direkt!
    }


    @Override
    public void draw(Graphics2D g2) {

    }
}
