package controllers;

import model.gameinterfaces.IGame;
import view.IObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the main game loop. It is responsible for updating the model and notifying potential
 * observers at given updates per second.
 * @author Herman Noren
 */
public class GameLoopController implements ActionListener {

    private final int UPS; // UpdatesPerSecond
    private final Timer timer;
    private final IGame game;
    private final List<IObserver> gameObservers;

    /**
     * Instantiates a GameLoopController. The controller will run at given tick rate.
     * @param game model class to update every tick.
     * @param updatesPerSecond tick rate. How often the controller updates the given model class and notifies
     *                         potential observers per second.
     */
    public GameLoopController(IGame game, int updatesPerSecond) {
        UPS = updatesPerSecond;
        this.game = game;
        timer = new Timer(1000 / UPS, this);
        gameObservers = new ArrayList<>();
    }

    /**
     * Add an observer.
     * @param observer observer to be notified each game tick
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
        timer.start();
    }

    /**
     * This method is called automatically by the internal timer. It updates the given model class and
     * then notifies potential observers to update.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!game.isPaused()) {
            game.update(100.0 / UPS);
        }

        for (IObserver observer : gameObservers) {
            observer.update();
        }
    }
}
