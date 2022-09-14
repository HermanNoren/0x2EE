package view.panelstates;

import gamestates.HowToPlayState;
import main.Game;
import view.MainPanel;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PanelStateFactory {

    public static IPanelState createPanelState(EPanelState state, MainPanel mainPanel)  {
        switch (state) {
            case INGAME -> {
                return new InGamePanelState(mainPanel);
            }
            case MAINMENU -> {
                return new MainMenuPanelState(mainPanel);
            }
            case HIGHSCORES -> {
                return new HighscorePanelState(mainPanel);
            }

            case PAUSE -> {
                return new PausePanelState(mainPanel);
            }

            case HOWTOPLAY -> {
                return new HowToPlayPanelState(mainPanel);
            }

            default -> {
                return null;
            }
        }
    }
}