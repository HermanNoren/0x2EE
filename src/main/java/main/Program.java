package main;

import controllers.GameLoopController;
import model.Game;
import model.gameobjects.Player;
import model.mapclasses.GameMap;
import view.MainPanel;
import view.Window;

public class Program {
    public static void main(String[] args){
        GameMap gameMap = new GameMap(10, 10);
        Game game = new Game();
        GameLoopController gameLoop = new GameLoopController();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);
        gameLoop.run(game);
    }

}