package view.panelstates;

import view.MainPanel;
import view.panelstates.*;

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

            case NEWHIGHSCORE -> {
                return new NewHighscorePanelState();
            }

            default -> {
                return null;
            }
        }
    }
}
