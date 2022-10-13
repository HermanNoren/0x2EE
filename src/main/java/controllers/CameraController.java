package controllers;
import view.Camera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CameraController implements KeyListener {

    private final Camera camera;
    private boolean upKeyDown;
    private boolean downKeyDown;

    public CameraController() {
        camera = Camera.getInstance();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case (KeyEvent.VK_UP) -> {
                upKeyDown = false;
            }
            case (KeyEvent.VK_DOWN) -> {
                downKeyDown = false;
            }
        }
    }
}
