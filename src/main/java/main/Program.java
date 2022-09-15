package main;

import view.MainPanel;
import view.Window;
import view.panelstates.InGamePanelState;
import view.panelstates.MainMenuPanelState;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.createGame();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);
    }
}



