package view;

import config.Config;
import helperclasses.Vector2;
import sprites.ISprite;

public class Camera {

    private ISprite focusedObject;
    private final Vector2 pos;
    private Vector2 center;

    public Camera(ISprite focusedObject) {
        this.focusedObject = focusedObject;
        pos = new Vector2(0, 0);
        calculateCenterPos();
    }

    public void setFocusedObject(ISprite object) {
        focusedObject = object;
    }

    public Vector2 getOffset() {
        return new Vector2(pos.x, pos.y);
    }

    public void update() {
        calculateCenterPos();
        pos.x += (focusedObject.getCenter().x - (pos.x + center.x)) / 120;
        pos.y += (focusedObject.getCenter().y - (pos.y + center.y)) / 120;
    }

    private void calculateCenterPos() {
        double x = (double) (Config.SCREEN_WIDTH / 2);
        double y = (double) (Config.SCREEN_HEIGHT / 2);
        center = new Vector2(x, y);
    }
}
