package main;

import controllers.GameLoopController;
import controllers.SpawnTimerController;
import model.Game;
import view.MainPanel;
import view.Window;

import java.util.Timer;

public class Program {
    public static void main(String[] args){
        Game game = new Game();
        GameLoopController gameLoop = new GameLoopController();
        MainPanel mainPanel = new MainPanel(game);
        game.addObserver(mainPanel);
        Window window = new Window(mainPanel);

        Timer timer = new Timer();
        timer.schedule(new SpawnTimerController(game), 5,5000);
        gameLoop.run(game);
    }

}