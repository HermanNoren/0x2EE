package controllers;

import model.gameinterfaces.ICanSpawnEnemy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for continuously spawning enemies in the game using a given delay.
 * @author Arthur Alexandersson
 */
public class SpawnTimerController implements ActionListener {
    private final ICanSpawnEnemy game;
    private final Timer timer;
    private int counter = 1;

    /**
     * Instantiates a SpawnTimerController
     * @param game object that can spawn enemies
     * @param delay ms between enemy spawns
     */
    public SpawnTimerController(ICanSpawnEnemy game, int delay){
        timer = new Timer(delay, this);
        this.game = game;
    }

    /**
     * Starts the controller
     */
    public void run(){
        timer.start();
    }

    /**
     * Pauses the controller
     */
    public void pause(){
        timer.stop();
    }

    /**
     * This method is called automatically by the internal timer. It delegates to the given ICanSpawnEnemy object to
     * spawn an enemy.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        game.spawnEnemy();
    }
}
