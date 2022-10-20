package controllers;
import view.Camera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This controller provides the ability to zoom the camera using the keyboard.
 * @author Herman Noren
 */
public class CameraZoomController implements ActionListener {

    private Timer zoomTimer;

    private EZoomDirection direction;

    public void start(EZoomDirection direction) {
        if (zoomTimer != null) {
            zoomTimer.stop();
        }
        zoomTimer = new Timer(5, new ZoomControllerHelper(direction));
        zoomTimer.start();
    }

    public void stop() {
        zoomTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction == EZoomDirection.IN) {
            Camera.getInstance().zoomIn();
        }
        if (direction == EZoomDirection.OUT) {
            Camera.getInstance().zoomOut();
        }
    }
}
