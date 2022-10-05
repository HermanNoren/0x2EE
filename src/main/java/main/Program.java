package main;

import controllers.GameLoopController;
import model.Game;
import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args){
        Game game = new Game();
        GameLoopController gameLoop = new GameLoopController();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);
        gameLoop.run(game);
    }

}