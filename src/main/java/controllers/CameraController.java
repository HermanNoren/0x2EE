package controllers;
import view.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This controller provides the ability to zoom the camera using the keyboard.
 * @author Herman Noren
 */
public class CameraController implements KeyListener {

    private final Camera camera;

    /**
     * Instantiates a camera controller
     */
    public CameraController() {
        camera = Camera.getInstance();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * {@inheritDoc}

     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case (KeyEvent.VK_UP) -> {
                camera.zoomIn();
            }
            case (KeyEvent.VK_DOWN) -> {
                camera.zoomOut();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
