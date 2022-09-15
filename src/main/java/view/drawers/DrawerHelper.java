package view.drawers;

import helperclasses.Vector2;
import view.Camera;

import java.util.ArrayList;

public class DrawerHelper {

    public static ArrayList<Integer> calculateDrawingInformation(Vector2 pos, int width, int height, Camera camera) {
        ArrayList<Integer> drawInformation = new ArrayList<>();

        double x = (pos.x - camera.getOffset().x) * camera.getZoomAmount();
        double y = (pos.y - camera.getOffset().y) * camera.getZoomAmount();
        double newWidth = width * camera.getZoomAmount();
        double newHeight = height * camera.getZoomAmount();

        drawInformation.add((int) Math.round(x));
        drawInformation.add((int) Math.round(y));
        drawInformation.add((int) Math.round(newWidth));
        drawInformation.add((int) Math.round(newHeight));

        return drawInformation;
    }

}
