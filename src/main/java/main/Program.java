package main;

import controllers.GameLoopController;
import controllers.SpawnTimerController;
import model.Game;
import model.gameinterfaces.IGameEnemy;
import view.MainPanel;
import view.Window;

import java.util.Timer;

/**
 * Program class, contains main method.
 */
public class Program {
    /**
     * @param args
     * The application. Method used to run the application.
     */
    public static void main(String[] args){
        IGameEnemy game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        Window window = new Window(mainPanel);
        GameLoopController loopController = new GameLoopController(game, 120);
        Timer enemySpawnTimer = new Timer();
        enemySpawnTimer.schedule(new SpawnTimerController(game), 5000, 5000);
        loopController.addObserver(mainPanel);
        loopController.run();
    }

}