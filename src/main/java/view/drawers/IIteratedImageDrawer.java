package view.drawers;

/**
 * Drawers that iterate through images can implement this interface to be able to use an ImageSwitcherController
 * to control the delay between switching images.
 */
public interface IIteratedImageDrawer {
    void switchImage();
}
