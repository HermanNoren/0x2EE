package view.panelstates;

import model.gameinterfaces.IGame;
import view.IChangeableStatePanel;

/**
 * Factory pattern which creates a new state method is called with correct parameters.x
 */
public class PanelStateFactory {

    public static IPanelState createPanelState(EPanelState state, IChangeableStatePanel mainPanel, IGame game)  {
        switch (state) {
            case INGAME -> {
                return new InGamePanelState(mainPanel, game);
            }
            case MAINMENU -> {
                return new MainMenuPanelState(mainPanel, game);
            }
            case HIGHSCORES -> {
                return new HighscorePanelState(mainPanel);
            }
            case SHOP -> {
                return new ShopPanelState(mainPanel, game.getShopTransaction(),game);
            }

            case PAUSE -> {
                return new PausePanelState(mainPanel, game);
            }

            case HOWTOPLAY -> {
                return new HowToPlayPanelState(mainPanel);
            }

            case NEWHIGHSCORE -> {
                return new NewHighscorePanelState(mainPanel, game);
            }

            case GAMEOVER ->  {
                return new GameOverPanelState(mainPanel, game);
            }

            default -> {
                return null;
            }
        }
    }
}
