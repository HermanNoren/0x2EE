package controllers;

import view.Camera;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BossIntroductionControllerHelper implements ActionListener {

    EBossControllerZoomDirection direction;

    public BossIntroductionControllerHelper(EBossControllerZoomDirection direction) {
        this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (direction == EBossControllerZoomDirection.IN) {
            Camera.getInstance().zoomIn();
        }
        if (direction == EBossControllerZoomDirection.OUT) {
            Camera.getInstance().zoomOut();
        }
    }
}
