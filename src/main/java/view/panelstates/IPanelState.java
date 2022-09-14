package view.panelstates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public interface IPanelState {
    void draw(Graphics2D g2);

    KeyListener getKeyListener();
}
