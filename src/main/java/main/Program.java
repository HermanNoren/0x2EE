package main;

import controllers.GameLoopController;
import model.Game;
import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args){
        Game game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        Window window = new Window(mainPanel);
        GameLoopController loopController = new GameLoopController(game, 120);
        loopController.addObserver(mainPanel);
        loopController.run();
    }

}