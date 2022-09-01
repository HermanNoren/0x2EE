package main;

import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args) {
        Game game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        game.addWindow(new Window(mainPanel));
        game.startGame();
    }
}



