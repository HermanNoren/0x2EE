package main;

import controllers.GameLoopController;
import model.Game;
import model.gameobjects.Player;
import model.mapclasses.GameMap;
import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args){
        Game game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        Window window = new Window(mainPanel);
        GameLoopController gameLoopController = new GameLoopController(game);
        gameLoopController.addObserver(mainPanel);
        gameLoopController.run();
    }

}