package main;

import gamestates.GameState;
import sprites.Player;
import sprites.Sprite;

import java.util.ArrayList;

public class Game implements Runnable {
    private Window window;
    private GamePanel gamePanel;
    private Thread gameLoopThread;
    private final int FPS = 120; // FRAMES PER SECOND
    private final int UPS = 200; // UPDATES PER SECOND

    private GameState state;

    private Player player;
    private ArrayList<Sprite> sprites;

    public Game() {
        player = new Player(0, 0);
        sprites = new ArrayList<>();
        sprites.add(player);
        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }

    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    private void update() {
        //state.update();
        for (Sprite sprite : sprites) {
            sprite.update();
        }
    }

    private void draw() {
        /*
        for (Observer o : state.getObservers()) {
            o.notify()
        }
         */
        gamePanel.repaint();
    }

    @Override
    public void run() {

        double drawTime =  1000000000.0 / FPS;
        double updateTime = 1000000000.0 / UPS;

        long currentTime;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastFpsCheck = System.currentTimeMillis();

        double deltaUpdateTime = 0;
        double deltaDrawTime = 0;

        while(true) {
            currentTime = System.nanoTime();

            deltaUpdateTime += (currentTime - previousTime) / updateTime;
            deltaDrawTime += (currentTime - previousTime) / drawTime;
            previousTime = currentTime;

            if (deltaUpdateTime >= 1) {
                update();
                updates++;
                deltaUpdateTime--;
            }

            if (deltaDrawTime >= 1) {
                draw();
                frames++;
                deltaDrawTime--;
            }

            /*
            if (now - lastFrame >= frameTime) {
                draw();
                lastFrame = now;
                frames++;
            }
            */

            if (System.currentTimeMillis() - lastFpsCheck >= 1000) {
                lastFpsCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}