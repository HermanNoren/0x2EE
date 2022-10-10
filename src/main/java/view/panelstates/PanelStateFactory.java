package view.panelstates;

import model.Game;
import view.MainPanel;

public class PanelStateFactory {

    public static IPanelState createPanelState(EPanelState state, MainPanel mainPanel, Game game)  {
        switch (state) {
            case INGAME -> {
                return new InGamePanelState(mainPanel, game);
            }
            case MAINMENU -> {
                return new MainMenuPanelState(mainPanel, game);
            }
            case HIGHSCORES -> {
                return new HighscorePanelState(mainPanel, game);
            }
            case SHOP -> {
                return new ShopPanelState(mainPanel, game.getShopTransaction());
            }

            case PAUSE -> {
                return new PausePanelState(mainPanel, game);
            }

            case HOWTOPLAY -> {
                return new HowToPlayPanelState(mainPanel, game);
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
