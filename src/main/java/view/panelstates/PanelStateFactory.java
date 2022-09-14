package view.panelstates;

import main.Game;
import view.MainPanel;

import javax.swing.*;

public class PanelStateFactory {

    public static IPanelState createPanelState(EStateTag state, MainPanel mainPanel)  {
        switch (state) {
            case INGAME -> {
                return new InGamePanelState(mainPanel);
            }
            case MAINMENU -> {
                return new MainMenuPanelState(mainPanel);
            }
            default -> {
                return null;
            }
        }
    }
}
