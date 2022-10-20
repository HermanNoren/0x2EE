package controllers;

import model.gameinterfaces.IGame;
import model.gameobjects.enemies.Enemy;
import view.Camera;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BossIntroductionController implements ActionListener {

    private final IGame game;
    private final Camera camera;
    private Timer pauseTimer;

    public BossIntroductionController(IGame game) {
        this.game = game;
        this.camera = Camera.getInstance();
    }

    public boolean listenForBossSpawn() {
        if (game.getBossSpawnedFlag()) {
            game.resetBossSpawnFlag();
            game.pause();
            List<Enemy> gameEnemies = game.getEnemies();
            camera.setFocusedObject(gameEnemies.get(gameEnemies.size()-1));
            camera.startZoomIn();
            pauseTimer = new Timer(2500, this);
            pauseTimer.start();
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pauseTimer.stop();
        camera.setFocusedObject(game.getPlayer());
        camera.startZoomOut();
        game.resume();
    }
}

