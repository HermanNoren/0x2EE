package view.panelstates;

import gamestates.HowToPlayState;
import main.Game;
import view.MainPanel;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PanelStateFactory {

    public static IPanelState createPanelState(EPanelState state)  {
        switch (state) {
            case INGAME -> {
                return new InGamePanelState();
            }
            case MAINMENU -> {
                return new MainMenuPanelState();
            }
            case HIGHSCORES -> {
                return new HighscorePanelState();
            }

            case PAUSE -> {
                return new PausePanelState();
            }

            case HOWTOPLAY -> {
                return new HowToPlayPanelState();
            }

            default -> {
                return null;
            }
        }
    }
}
