package model.helperclasses;

import model.helperclasses.Vector2;
import view.Camera;
import view.drawers.IDrawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DrawerHelper class is made to aid the representation of the sprite relative to the camera-view.
 */
public class DrawerHelper implements IDrawer {

    public static List<Integer> calculateDrawingInformation(Vector2 pos, int width, int height) {
        ArrayList<Integer> drawInformation = new ArrayList<>();
        Camera camera = Camera.getInstance();

        double x = (pos.x - camera.getOffset().x) * camera.getZoomMultiplier();
        double y = (pos.y - camera.getOffset().y) * camera.getZoomMultiplier();
        return smellRemover(width, height, drawInformation, camera, x, y);
    }

    public static List<Integer> calculateDrawingInformation(int x_coordinate, int y_coordinate, int width, int height) {
        List<Integer> drawInformation = new ArrayList<>();
        Camera camera = Camera.getInstance();

        double x = (x_coordinate - camera.getOffset().x) * camera.getZoomMultiplier();
        double y = (y_coordinate - camera.getOffset().y) * camera.getZoomMultiplier();
        return smellRemover(width, height, drawInformation, camera, x, y);
    }



    private static List<Integer> smellRemover(int width, int height, List<Integer> drawInformation, Camera camera, double x, double y) {
        double newWidth = width * camera.getZoomMultiplier();
        double newHeight = height * camera.getZoomMultiplier();

        drawInformation.add((int) Math.floor(x));
        drawInformation.add((int) Math.floor(y));
        drawInformation.add((int) Math.ceil(newWidth));
        drawInformation.add((int) Math.ceil(newHeight));

        return drawInformation;
    }


    @Override
    public void draw(Graphics2D g2) {

    }

}
