package view;

import config.Config;
import helperclasses.Vector2;
import sprites.ISprite;

import java.awt.geom.CubicCurve2D;

public class Camera {

    private ISprite focusedObject;
    private Vector2 pos;
    private Vector2 absolutePos;
    private Vector2 center;
    private int dragEffectConstant;
    private double currentZoomAmount;
    private final double totalZoomAmount;
    private boolean zoomStarted;
    private boolean resetZoomStarted;

    public Camera(ISprite focusedObject) {
        this.focusedObject = focusedObject;
        pos = new Vector2(100, 100);
        absolutePos = new Vector2(pos);
        dragEffectConstant = 120;
        currentZoomAmount = 1;
        totalZoomAmount = 1.5;
        zoomStarted = false;
        resetZoomStarted = false;
        calculateCenterPos();
    }

    public void setFocusedObject(ISprite object) {
        focusedObject = object;
    }

    public Vector2 getOffset() {
        return new Vector2(pos.x, pos.y);
    }

    public void zoomIn() {
        currentZoomAmount += 0.2;
        if (currentZoomAmount >= 4) { currentZoomAmount = 4; }
    }

    public void zoomOut() {
        currentZoomAmount -= 0.2;
        if (currentZoomAmount <= 1) { currentZoomAmount = 1; }
    }

    public double getZoomAmount() {
        return currentZoomAmount;
    }

    public void setDragEffectConstant(int value) {
        dragEffectConstant = value;
    }

    public void resetDragEffectConstant() {
        dragEffectConstant = 50;
    }

    public void update() {
        calculateCenterPos();
        pos = new Vector2(absolutePos);
        pos.x += (focusedObject.getCenter().x - (pos.x + center.x)) / (dragEffectConstant / currentZoomAmount);
        pos.y += (focusedObject.getCenter().y - (pos.y + center.y)) / (dragEffectConstant / currentZoomAmount);
        absolutePos = new Vector2(pos);

        pos.x += (Config.SCREEN_WIDTH - Config.SCREEN_WIDTH / currentZoomAmount) / 2;
        pos.y += (Config.SCREEN_HEIGHT - Config.SCREEN_HEIGHT / currentZoomAmount) / 2;

    }

    private void calculateCenterPos() {
        double x = Config.SCREEN_WIDTH / 2.0;
        double y = Config.SCREEN_HEIGHT / 2.0;
        center = new Vector2(x, y);
    }
}
