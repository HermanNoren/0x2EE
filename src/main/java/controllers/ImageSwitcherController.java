package controllers;

import view.drawers.IIteratedImageDrawer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ImageSwitcherController implements ActionListener{

    private final Timer timer;

    private List<IIteratedImageDrawer> imageDrawers;

    public ImageSwitcherController(int delay) {
        imageDrawers = new ArrayList<>();
        timer = new Timer(delay, this);
    }

    public void start() {
        timer.start();
    }

    public void addImageDrawer(IIteratedImageDrawer imageDrawer) {
        imageDrawers.add(imageDrawer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (IIteratedImageDrawer imageDrawer : imageDrawers) {
            imageDrawer.switchImage();
        }
    }
}
