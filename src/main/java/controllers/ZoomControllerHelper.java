package controllers;

import view.Camera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZoomControllerHelper implements ActionListener {

    EZoomDirection direction;

    public ZoomControllerHelper(EZoomDirection direction) {
        this.direction = direction;
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
