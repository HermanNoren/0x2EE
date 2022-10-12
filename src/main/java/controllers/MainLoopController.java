package controllers;

import model.Game;
import view.IObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainLoopController implements ActionListener {

    private final int UPS; // UpdatesPerSecond
    private Timer timer;
    private List<Game> gameInstances;
    private List<IObserver> gameObservers;

    private static MainLoopController mainLoopController;

    private MainLoopController() {
        UPS = 200;
        gameObservers = new ArrayList<>();
        gameInstances = new ArrayList<>();
    }

    public static MainLoopController getInstance() {
        if (mainLoopController == null){
            mainLoopController = new MainLoopController();
        } return mainLoopController;
    }

    public void addModel(Game game) {
        gameInstances = new ArrayList<>();
        gameInstances.add(game);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Game game : gameInstances) {
            if (!game.isPaused()) {
                game.update(100.0 / UPS);
            }
        }

        for (IObserver observer : gameObservers) {
            observer.update();
        }
    }
}
