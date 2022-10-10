package controllers;

import model.Game;
import view.IObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameLoopController implements ActionListener {

    private final int FPS;
    private final int UPS;
    Timer timer;

    private List<IObserver> gameObservers;

    Game game;

    public GameLoopController(Game game) {
        FPS = 120;
        UPS = 200;
        gameObservers = new ArrayList<>();
        this.game = game;
    }

    /**
     * Add an observer.
     * @param observer observer
     */
    public void addObserver(IObserver observer) {
        gameObservers.add(observer);
    }

    /**
     * Main game loop.
     * Handles logic of when to update the internal game
     * classes and when to notify potential observers.
     */
    public void run() {

        timer = new Timer(1000 / UPS, this);
        timer.start();

        /*
        double timePerRender =  1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
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
                game.update(100.0 / UPS);
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
         */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update(100.0 / UPS);

        for (IObserver observer : gameObservers) {
            observer.update();
        }
    }
}
