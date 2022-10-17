package controllers;

import model.gameinterfaces.ICanSpawnEnemy;

import java.util.TimerTask;

public class SpawnTimerController extends TimerTask {
    private ICanSpawnEnemy game;
    private int counter = 0;
    public SpawnTimerController(ICanSpawnEnemy game){
        this.game = game;
    }
    @Override
    public void run() {
        game.spawnEnemy(counter);
        counter++;
    }

}
