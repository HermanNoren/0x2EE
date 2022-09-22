package main;

import controllers.GameLoopController;
import model.Game;
import view.MainPanel;
import view.Window;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        game.createGame();
        GameLoopController gameLoop = new GameLoopController();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);
        gameLoop.run(game);
    }
}



