package view.panelstates;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

public interface IPanelState {
    void draw(Graphics2D g2);
    void changePanelState(EPanelState panelState);
    List<KeyListener> getKeyListeners();
}
