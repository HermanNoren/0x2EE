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

    public void zoom() {
        zoomStarted = true;
    }

    public void resetZoom() {
        resetZoomStarted = true;
    }

    public double getZoomAmount() {
        return currentZoomAmount;
    }

    public void halfDragEffectConstant() {
        dragEffectConstant = 15;
    }

    public void resetDragEffectConstant() {
        dragEffectConstant = 50;
    }

    public void update() {
        if (zoomStarted) {
            currentZoomAmount *= 1.01;
        }
        if (currentZoomAmount >= totalZoomAmount) {
            currentZoomAmount = totalZoomAmount;
            zoomStarted = false;
        }
        if (resetZoomStarted) {
            currentZoomAmount /= 1.01;
        }
        if (currentZoomAmount <= 1) {
            currentZoomAmount = 1;
            resetZoomStarted = false;
        }

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
