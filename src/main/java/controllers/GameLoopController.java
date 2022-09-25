package controllers;

import config.Config;
import model.Game;

public class GameLoopController {

    /**
     * Main game loop.
     * Handles logic of when to update the internal game
     * classes and when to notify potential observers.
     */
    public void run(Game game) {

        double timePerRender =  1000000000.0 / Config.FPS;
        double timePerUpdate = 1000000000.0 / Config.UPS;
        double deltaUpdateTime = 0;
        double deltaDrawTime = 0;
        long currentTime;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastFpsCheck = System.currentTimeMillis();

        while(true) {
            currentTime = System.nanoTime();
            deltaUpdateTime += (currentTime - previousTime) / timePerUpdate;
            deltaDrawTime += (currentTime - previousTime) / timePerRender;
            previousTime = currentTime;

            if (deltaUpdateTime >= 1) {
                game.update();
                updates++;
                deltaUpdateTime--;
            }

            if (deltaDrawTime >= 1) {
                game.notifyObservers(); //DRAW
                frames++;
                deltaDrawTime--;
            }

            if (System.currentTimeMillis() - lastFpsCheck >= 1000) {
                lastFpsCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

}
