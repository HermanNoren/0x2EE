package main;

import view.MainPanel;
import view.Window;
import view.panelstates.InGamePanel;

public class Program {
    public static void main(String[] args) {
        Game game = new Game();
        MainPanel mainPanel = new MainPanel(game, new InGamePanel(game));
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);
    }
}



