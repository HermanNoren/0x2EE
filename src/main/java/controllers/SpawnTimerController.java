package controllers;

import model.Game;
import model.gameinterfaces.ICanSpawnEnemy;
import model.gameinterfaces.IGame;
import view.IObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SpawnTimerController implements ActionListener {
    private ICanSpawnEnemy game;
    private Timer timer;
    private int counter = 1;
    public SpawnTimerController(ICanSpawnEnemy game, int delay){
        timer = new Timer(delay, this);
        this.game = game;
    }
    public void run(){
        timer.start();
    }
    public void pause(){
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.spawnEnemy(counter);
        counter++;
    }

}
