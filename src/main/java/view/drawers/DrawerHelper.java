package view.drawers;

import model.helperclasses.Vector2;
import view.Camera;

import java.util.ArrayList;

public class DrawerHelper {

    public static ArrayList<Integer> calculateDrawingInformation(Vector2 pos, int width, int height) {
        ArrayList<Integer> drawInformation = new ArrayList<>();
        Camera camera = Camera.getInstance();

        double x = (pos.x - camera.getOffset().x) * camera.getZoomMultiplier();
        double y = (pos.y - camera.getOffset().y) * camera.getZoomMultiplier();
        double newWidth = width * camera.getZoomMultiplier();
        double newHeight = height * camera.getZoomMultiplier();

        drawInformation.add((int) Math.round(x));
        drawInformation.add((int) Math.round(y));
        drawInformation.add((int) Math.round(newWidth));
        drawInformation.add((int) Math.round(newHeight));

        return drawInformation;
    }

}
