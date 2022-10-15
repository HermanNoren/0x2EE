package controllers;

import model.gameinterfaces.ISpawnable;

import java.util.TimerTask;

public class SpawnTimerController extends TimerTask {
    private ISpawnable game;
    private int counter = 0;
    public SpawnTimerController(ISpawnable game){
        this.game = game;
    }
    @Override
    public void run() {
        game.spawnEnemy(counter);
        counter++;
    }

}
