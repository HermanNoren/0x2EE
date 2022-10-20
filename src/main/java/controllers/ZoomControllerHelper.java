package controllers;

import view.Camera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used together with CameraZoomController to zoom the camera in / out. This method's actionPerformed
 * method is called multiple times by CameraZoomControllers Timer.
 * @author Herman Noren
 */
public class ZoomControllerHelper implements ActionListener {

    private final EZoomDirection direction;

    /**
     * Instantiates a ZoomControllerHelper
     * @param direction the direction which to zoom in
     */
    public ZoomControllerHelper(EZoomDirection direction) {
        this.direction = direction;
    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Camera camera = Camera.getInstance();
        if (direction == EZoomDirection.IN) {
            camera.zoomIn();
        }
        if (direction == EZoomDirection.OUT) {
            camera.zoomOut();
        }
    }
}
