package main;

import controllers.GameLoopController;
import model.Game;
import model.gameinterfaces.IGame;
import view.MainPanel;
import view.Window;


/**
 * Program class, contains main method.
 */
public class Program {
    /**
     * @param args
     * The application. Method used to run the application.
     */
    public static void main(String[] args){
        IGame game = new Game();
        MainPanel mainPanel = new MainPanel(game);
        Window window = new Window(mainPanel);
        GameLoopController loopController = new GameLoopController(game, 120);

        loopController.addObserver(mainPanel);
        loopController.run();
    }

}