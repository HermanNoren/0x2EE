package controllers;

import model.Game;

import java.util.TimerTask;

public class SpawnTimerController extends TimerTask {
    private Game game;
    private int counter = 0;
    public SpawnTimerController(Game game){
        this.game = game;
    }
    @Override
    public void run() {
        game.spawnEnemy(counter);
        System.out.println("enemy spawned");
        counter++;
    }

}
