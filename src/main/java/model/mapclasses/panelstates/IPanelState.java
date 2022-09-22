package model.mapclasses.panelstates;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public interface IPanelState {
    void draw(Graphics2D g2);
    void changePanelState(EPanelState panelState);
    ArrayList<KeyListener> getKeyListeners();
}
