package view.drawers;

import model.Vector2;
import view.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * The DrawerHelper class is made to aid the representation of the sprite relative to the camera-view.
 * @author Herman Noren
 */
public class DrawerHelper {

    /**
     * Method used for calculating correct positioning and sizing when drawing on the screen while taking the
     * camera into consideration. Drawers that want to take the camera offset and zooming into consideration
     * should use this method to calculate where to draw.
     * @param pos The object that is being drawns absolute position
     * @param width The objects that is being drawns absolute width
     * @param height The objects that is being drawns absolute height
     * @return A list containing the new drawing information in this order: [newXPos, newYPos, newWidth, newHeight].
     */
    public static List<Integer> calculateDrawingInformation(Vector2 pos, int width, int height) {
        ArrayList<Integer> drawInformation = new ArrayList<>();
        Camera camera = Camera.getInstance();

        double newXPos = (pos.getX() - camera.getOffset().getX()) * camera.getZoomMultiplier();
        double newYPos = (pos.getY() - camera.getOffset().getY()) * camera.getZoomMultiplier();
        double newWidth = width * camera.getZoomMultiplier();
        double newHeight = height * camera.getZoomMultiplier();

        drawInformation.add((int) Math.floor(newXPos));
        drawInformation.add((int) Math.floor(newYPos));
        drawInformation.add((int) Math.ceil(newWidth));
        drawInformation.add((int) Math.ceil(newHeight));

        return drawInformation;
    }
}
