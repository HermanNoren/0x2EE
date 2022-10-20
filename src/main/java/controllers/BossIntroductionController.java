package controllers;

import controllers.sound.SoundEffect;
import model.Game;
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

    private final SoundEffect soundEffect;

    private Timer pauseTimer;
    private Timer zoomTimer;

    public BossIntroductionController(IGame game) {
        this.game = game;
        this.camera = Camera.getInstance();
        this.soundEffect = new SoundEffect();
        soundEffect.setSoundFile("src/main/resources/sound/shrek.wav");
    }

    public void listenForBossSpawn() {
        if (game.getBossSpawnedFlag()) {
            game.resetBossSpawnFlag();
            soundEffect.play();
            game.pause();
            List<Enemy> gameEnemies = game.getEnemies();
            camera.setFocusedObject(gameEnemies.get(gameEnemies.size()-1));
            zoomTimer = new Timer(20, new BossIntroductionControllerHelper(EBossControllerZoomDirection.IN));
            zoomTimer.start();
            pauseTimer = new Timer(2000, this);
            pauseTimer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pauseTimer.stop();
        zoomTimer.stop();
        camera.setFocusedObject(game.getPlayer());
        zoomTimer = new Timer(20, new BossIntroductionControllerHelper(EBossControllerZoomDirection.OUT));
        zoomTimer.start();
        game.resume();
    }
}

