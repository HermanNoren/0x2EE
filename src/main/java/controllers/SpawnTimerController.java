package controllers;

import model.gameinterfaces.ICanSpawn;

import java.util.TimerTask;

public class SpawnTimerController extends TimerTask {
    private ICanSpawn game;
    private int counter = 0;
    public SpawnTimerController(ICanSpawn game){
        this.game = game;
    }
    @Override
    public void run() {
        game.spawnEnemy(counter);
        counter++;
    }

}
