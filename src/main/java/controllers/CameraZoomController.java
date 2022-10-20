package controllers;

import javax.swing.*;

/**
 * This controller provides the ability to zoom the camera using the keyboard.
 * @author Herman Noren
 */
public class CameraZoomController {

    private Timer zoomTimer;

    public void start(EZoomDirection direction) {
        if (zoomTimer != null) {
            zoomTimer.stop();
        }
        zoomTimer = new Timer(5, new ZoomControllerHelper(direction));
        zoomTimer.start();
    }

    public void stop() {
        if (zoomTimer == null) { return; }
        zoomTimer.stop();
    }
}
