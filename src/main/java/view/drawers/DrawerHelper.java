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

        drawInformation.add((int) x);
        drawInformation.add((int) y);
        drawInformation.add((int) newWidth);
        drawInformation.add((int) newHeight);

        return drawInformation;
    }

}
