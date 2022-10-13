package controllers;

import view.drawers.IIteratedImageDrawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * ImageSwitcherController handles the delay between image switches in classes that implement the IIteratedImageDrawer.
 */
public class ImageSwitcherController implements ActionListener {

    private final Timer timer;

    private List<IIteratedImageDrawer> imageDrawers;

    /**
     * Instantiates an ImageSwitcherController.
     * @param delay ms between image switch
     */
    public ImageSwitcherController(int delay) {
        imageDrawers = new ArrayList<>();
        timer = new Timer(delay, this);
    }

    /**
     * Start the internal timer.
     */
    public void start() {
        timer.start();
    }

    /**
     * Add a drawer that the controller should call upon every 'delay' milliseconds
     * @param imageDrawer
     */
    public void addImageDrawer(IIteratedImageDrawer imageDrawer) {
        imageDrawers.add(imageDrawer);
    }

    /**
     * Calls upon every added drawer to switch image. This method is automatically called by the internal timer
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (IIteratedImageDrawer imageDrawer : imageDrawers) {
            imageDrawer.switchImage();
        }
    }
}
