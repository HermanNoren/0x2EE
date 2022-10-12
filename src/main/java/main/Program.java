package main;

import controllers.MainLoopController;
import model.Game;
import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args){
        Game game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        Window window = new Window(mainPanel);
        MainLoopController loopController = MainLoopController.getInstance();
        loopController.addModel(game);
        loopController.addObserver(mainPanel);
        loopController.run();
    }

}