package view;

import config.Config;
import model.Game;
import model.gameobjects.IFocusableObject;
import model.helperclasses.Vector2;

import java.util.ArrayList;

/**
 * The camera class is used to simulate a camera that follows a chosen object. What object the camera is to follow
 * can be dynamically changed. Using the chosen object the camera class then calculates an offset which every
 * object being drawn should take into consideration if they have positions relative to the camera. The camera also
 * provides the ability to zoom in and out using a zoom multiplier that objects relative to the camera also should take
 * into consideration when being drawn.
 */
public class Camera {

    private ArrayList<IFocusableObject> focusedObject;
    private Vector2 relativePos, absolutePos, center;
    private int dragEffectConstant;
    private double currentZoomMultiplier;

    private static Camera camera;

    /**
     * Instantiates a camera object
     */
    private Camera() {
        this.focusedObject = new ArrayList<>();
        relativePos = new Vector2(0, 0);  // Relative pos will be used when calculating zoom
        absolutePos = new Vector2(relativePos);     // Absolute pos will not take zoom into consideration
        dragEffectConstant = 60;
        currentZoomMultiplier = 1;
        calculateCenterPos();
    }

    public static Camera getInstance() {
        if (camera == null){
            camera = new Camera();
        } return camera;
    }

    /**
     * Used for changing what object the camera is focusing
     * @param object Object the camera will focus
     */
    public void setFocusedObject(IFocusableObject object) {
        focusedObject = new ArrayList<>();
        focusedObject.add(object);
    }

    /**
     * Returns the offset of which everything that is relative to the camera will use to calculate a drawing position.
     * @return Offset vector
     */
    public Vector2 getOffset() {
        return new Vector2(relativePos.x, relativePos.y);
    }

    /**
     * Zoom in
     */
    public void zoomIn() {
        currentZoomMultiplier += 0.2;
        if (currentZoomMultiplier >= 4) { currentZoomMultiplier = 4; }
    }

    /**
     * Zoom out
     */
    public void zoomOut() {
        currentZoomMultiplier -= 0.2;
        if (currentZoomMultiplier <= 1) { currentZoomMultiplier = 1; }
    }

    /**
     * Returns the current zoom multiplier.
     * @return current zoom multiplier
     */
    public double getZoomMultiplier() {
        return currentZoomMultiplier;
    }

    /**
     * Provides the ability to change how much the camera is "dragging" after the focused object. A higher value
     * will result in more drag effect. Values below 1 is not accepted and will automatically turn off the drag effect.
     * @param value drag effect
     */
    public void setDragEffectConstant(int value) {
        if (value < 1) { value = 1; }
        dragEffectConstant = value;
    }

    /**
     * Resets the drag effect to its standard value
     */
    public void resetDragEffectConstant() {
        dragEffectConstant = 60;
    }

    /**
     * Updates the cameras position in regard to the object in focus
     */
    public void update() {
        calculateCenterPos();

        relativePos = new Vector2(absolutePos);
        for (IFocusableObject object : focusedObject) {
            relativePos.x += (object.getCenter().x - (relativePos.x + center.x)) / (dragEffectConstant / currentZoomMultiplier);
            relativePos.y += (object.getCenter().y - (relativePos.y + center.y)) / (dragEffectConstant / currentZoomMultiplier);
        }
        absolutePos = new Vector2(relativePos);

        relativePos.x += (Config.SCREEN_WIDTH - Config.SCREEN_WIDTH / currentZoomMultiplier) / 2;
        relativePos.y += (Config.SCREEN_HEIGHT - Config.SCREEN_HEIGHT / currentZoomMultiplier) / 2;

    }

    /**
     * Calculates the center point of the window
     */
    private void calculateCenterPos() {
        double x = Config.SCREEN_WIDTH / 2.0;
        double y = Config.SCREEN_HEIGHT / 2.0;
        center = new Vector2(x, y);
    }
}
