package view;

import config.Config;
import model.gameobjects.IFocusableObject;
import model.Vector2;

/**
 * The camera class is used to simulate a camera that follows a chosen object. What object the camera is to follow
 * can be dynamically changed. Using the chosen object the camera class then calculates an offset which every
 * object being drawn should take into consideration if they have positions relative to the camera. The camera also
 * provides the ability to zoom in and out by using a zoom multiplier that objects relative to the camera also should take
 * into consideration when being drawn.
 * @author Herman Noren
 * @responsibility Provides an offset and size information that objects that are being drawn should use to be
 * drawn in the correct position.
 */
public final class Camera{

    private IFocusableObject focusedObject;
    private boolean hasFocusedObject;
    private Vector2 relativePos, absolutePos;
    private final int width, height;
    private final Vector2 screenCenter;
    private final int standardGlideConstant;
    private int glideConstant;
    private double currentZoomMultiplier;
    private boolean borderLimited;
    private int leftBorderLimit, rightBorderLimit, topBorderLimit, bottomBorderLimit;

    private static Camera camera;

    /**
     * Instantiates a camera object
     */
    private Camera() {
        this.focusedObject = null;
        relativePos = new Vector2(0, 0);  // Relative pos will be used when calculating zoom
        absolutePos = new Vector2(relativePos);     // Absolute pos will not take zoom into consideration
        standardGlideConstant = 50;
        glideConstant = standardGlideConstant;
        currentZoomMultiplier = 1;
        borderLimited = false;
        hasFocusedObject = false;
        screenCenter = new Vector2(Config.SCREEN_WIDTH / 2.0, Config.SCREEN_HEIGHT / 2.0);
        width = Config.SCREEN_WIDTH;
        height = Config.SCREEN_HEIGHT;
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
     * Resets the camera; removes the focused object, resets the offset to 0x0, resets the drag effect constant,
     * resets the zoom multiplier and removes any border limitations.
     */
    public void reset() {
        focusedObject = null;
        relativePos = new Vector2(0, 0);
        absolutePos = new Vector2(relativePos);
        resetGlideConstant();
        removeBorderLimit();
        hasFocusedObject = false;
        currentZoomMultiplier = 1;
    }

    /**
     * Used for changing what object the camera is focusing
     * @param object Object the camera will focus
     */
    public void setFocusedObject(IFocusableObject object) {
        hasFocusedObject = true;
        focusedObject = object;
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
        return new Vector2(relativePos.getX() + screenCenter.getX() / currentZoomMultiplier,
                           relativePos.getY() + screenCenter.getY() / currentZoomMultiplier);
    }
    /**
     * Zoom in
     */
    public void zoomIn() {
        currentZoomMultiplier *= 1.05;
        if (currentZoomMultiplier >= 4) { currentZoomMultiplier = 4; }
    }

    /**
     * Zoom out
     */
    public void zoomOut() {
        currentZoomMultiplier *= 0.95;
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
     * Provides the ability to change how much the camera is "gliding" after the focused object. A higher value
     * will result in more glide effect. 1 equals no glide effect. Values below 1 is not accepted and will
     * automatically turn off the glide effect.
     * @param value drag effect >= 1
     */
    public void setGlideConstant(int value) {
        if (value < 1)
        {glideConstant = 1; return;}
        glideConstant = value;   
    }

    /**
     * Resets the glide effect to its standard value. The standard value is 50
     */
    public void resetGlideConstant() {
        glideConstant = standardGlideConstant;
    }

    /**
     * Allows the possibility to make the camera movement limited by a border. The difference between the left border
     * limit and the right border limit must be equal to or larger than the screen width. Likewise, the difference
     * between top and bottom border limits must be equal to or larger than the screen height. Violation of this will
     * result in the method call being ignored.
     * @param leftBorderLimit any integer
     * @param rightBorderLimit any integer >= leftBorderLimit + screenWidth
     * @param topBorderLimit any integer
     * @param bottomBorderLimit any integer >= topBorderLimit + screenHeight
     */
    public void setBorderLimit(int leftBorderLimit, int rightBorderLimit, int topBorderLimit, int bottomBorderLimit) {
        if (Math.abs(leftBorderLimit - rightBorderLimit) < width ||
                Math.abs(topBorderLimit - bottomBorderLimit) < height) {
            return;
        }
        borderLimited = true;
        this.leftBorderLimit = leftBorderLimit;
        this.rightBorderLimit = rightBorderLimit;
        this.topBorderLimit = topBorderLimit;
        this.bottomBorderLimit = bottomBorderLimit;
    }

    /**
     * Removes any border limitations
     */
    public void removeBorderLimit() {
        borderLimited = false;
    }

    /**
     * Updates the camera offset in regard to the object in focus
     */
    public void update() {
        if (hasFocusedObject) {
            absolutePos.setX(absolutePos.getX() + (focusedObject.getCenter().getX() -
                    (absolutePos.getX() + screenCenter.getX())) / (glideConstant / currentZoomMultiplier));
            absolutePos.setY(absolutePos.getY() + (focusedObject.getCenter().getY() -
                    (absolutePos.getY() + screenCenter.getY())) / (glideConstant / currentZoomMultiplier));
        }

        relativePos.setX(absolutePos.getX() + ((width - width / currentZoomMultiplier) / 2));
        relativePos.setY(absolutePos.getY() + ((height - height / currentZoomMultiplier) / 2));

        if (borderLimited) {
            fixCameraBoundariesAbsolutePos();
            fixCameraBoundariesRelativePos();
        }
    }

    private void fixCameraBoundariesAbsolutePos() {
        if (absolutePos.getX() < leftBorderLimit - (width - width / currentZoomMultiplier) / 2) {
            absolutePos.setX(leftBorderLimit - (width - width / currentZoomMultiplier) / 2);
        }
        if (absolutePos.getX() + width >
            rightBorderLimit + (width - width / currentZoomMultiplier) / 2) {
            absolutePos.setX(rightBorderLimit - width + (width - width / currentZoomMultiplier) / 2);
        }
        if (absolutePos.getY() < topBorderLimit - (height - height / currentZoomMultiplier) / 2) {
            absolutePos.setY(topBorderLimit - (height - height / currentZoomMultiplier) / 2);
        }
        if (absolutePos.getY() + height >
            bottomBorderLimit + (height - height / currentZoomMultiplier) / 2) {
            absolutePos.setY(bottomBorderLimit - height + (height - height / currentZoomMultiplier) / 2);
        }
    }

    private void fixCameraBoundariesRelativePos() {
        if (relativePos.getX() < leftBorderLimit) {
            relativePos.setX(leftBorderLimit);
        }
        if (relativePos.getX() + width / currentZoomMultiplier > rightBorderLimit) {
            relativePos.setX(rightBorderLimit - width / currentZoomMultiplier);
        }
        if (relativePos.getY() < topBorderLimit) {
            relativePos.setY(topBorderLimit);
        }
        if (relativePos.getY() + height / currentZoomMultiplier > bottomBorderLimit) {
            relativePos.setY(bottomBorderLimit - height / currentZoomMultiplier);
        }
    }
}