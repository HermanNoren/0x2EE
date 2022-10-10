package view;

import config.Config;
import model.gameobjects.IFocusableObject;
import model.helperclasses.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * The camera class is used to simulate a camera that follows a chosen object. What object the camera is to follow
 * can be dynamically changed. Using the chosen object the camera class then calculates an offset which every
 * object being drawn should take into consideration if they have positions relative to the camera. The camera also
 * provides the ability to zoom in and out using a zoom multiplier that objects relative to the camera also should take
 * into consideration when being drawn.
 */
public class Camera{

    private List<IFocusableObject> focusedObject;
    private Vector2 relativePos, absolutePos, screenCenter;
    private final int standardDragEffectConstant;
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
        standardDragEffectConstant = 50;
        dragEffectConstant = standardDragEffectConstant;
        currentZoomMultiplier = 1;
        calculateCenterPos();
    }

    /**
     * Provides Singleton Pattern. If the camera hasn't been instantiated yet this method will create a new instance,
     * otherwise it will return the current instance.
     * @return Camera
     */
    public static Camera getInstance() {
        if (camera == null){
            camera = new Camera();
        } return camera;
    }

    /**
     * Resets the camera; removes the focused object, resets the offset to 0x0, resets the drag effect constant
     * and resets the zoom multiplier.
     */
    public void reset() {
        this.focusedObject = new ArrayList<>();
        relativePos = new Vector2(0, 0);
        absolutePos = new Vector2(relativePos);
        resetDragEffectConstant();
        currentZoomMultiplier = 1;
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
        return new Vector2(relativePos.getX(), relativePos.getY());
    }

    /**
     * Returns the center position of the visible area inside the camera.
     * @return Center vector
     */
    public Vector2 getCenter() {
        return new Vector2(absolutePos.getX() + screenCenter.getX(), absolutePos.getY() + screenCenter.getY());
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
     * will result in more drag effect. 1 equals no drag effect. Values below 1 is not accepted and will
     * automatically turn off the drag effect.
     * @param value drag effect >= 1
     */
    public void setDragEffectConstant(int value) {
        if (value < 1) { value = 1; }
        dragEffectConstant = value;
    }

    /**
     * Resets the drag effect to its standard value. The standard value is 50
     */
    public void resetDragEffectConstant() {
        dragEffectConstant = standardDragEffectConstant;
    }

    /**
     * Updates the camera offset in regard to the object in focus
     */
    public void update() {
        relativePos = new Vector2(absolutePos);
        for (IFocusableObject object : focusedObject) {
            relativePos.setX(relativePos.getX() + (object.getCenter().getX() - (relativePos.getX() + screenCenter.getX())) / (dragEffectConstant / currentZoomMultiplier));
            relativePos.setY(relativePos.getY() + (object.getCenter().getY() - (relativePos.getY() + screenCenter.getY())) / (dragEffectConstant / currentZoomMultiplier));
        }

        absolutePos = new Vector2(relativePos);

        // cameraBoundaries(absolutePos);

        relativePos.setX(relativePos.getX() + ((Config.SCREEN_WIDTH - Config.SCREEN_WIDTH / currentZoomMultiplier) / 2));
        relativePos.setY(relativePos.getY() + ((Config.SCREEN_HEIGHT - Config.SCREEN_HEIGHT / currentZoomMultiplier) / 2));

        cameraBoundaries(relativePos);
    }

    private void cameraBoundaries(Vector2 absolutePos) {
        if (absolutePos.getX() < 0) {
            absolutePos.setX(0);
        }
        if (absolutePos.getX() + Config.SCREEN_WIDTH > Config.MAP_SIZE * Config.TERRAIN_SIZE) {
            absolutePos.setX(Config.MAP_SIZE * Config.TERRAIN_SIZE - Config.SCREEN_WIDTH);
        }
        if (absolutePos.getY() < 0) {
            absolutePos.setY(0);
        }
        if (absolutePos.getY() + Config.SCREEN_HEIGHT > Config.MAP_SIZE * Config.TERRAIN_SIZE) {
            absolutePos.setY(Config.MAP_SIZE * Config.TERRAIN_SIZE - Config.SCREEN_HEIGHT);
        }
    }

    /**
     * Calculates the center point of the window
     */
    private void calculateCenterPos() {
        double x = Config.SCREEN_WIDTH / 2.0;
        double y = Config.SCREEN_HEIGHT / 2.0;
        screenCenter = new Vector2(x, y);
    }
}
