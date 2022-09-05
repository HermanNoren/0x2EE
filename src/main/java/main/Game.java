package main;

import gamestates.GameState;
import gamestates.InGame;
import sprites.Player;
import sprites.Sprite;
import java.util.ArrayList;

public class Game implements Runnable {
    private Window window;
    private Thread gameLoopThread;
    private final int FPS = 120; // FRAMES PER SECOND
    private final int UPS = 200; // UPDATES PER SECOND

    private ArrayList<Observer> observers;
    private GameState state;

    public Game() {
        observers = new ArrayList<>();
        state = new InGame();
    }

    public Player getPlayer() {
        return state.getPlayer();
    }


    public void setState(GameState state) {
        this.state = state;
    }

    // ta inte in argument utan ändra till exempelvis
    // be controller kalla på rätt state-metod istället.
    //      public void setPausedStateAndSaveEarlier() {
    //          this.Savedstate = Savedstate;
    //          this.state = pausedState;
    //}
    //


    public ArrayList<Sprite> getSprites() {
        return state.getSprites();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void addWindow(Window window) {
        this.window = window;
    }

    public void startGame() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    private void update() {
        state.update();
    }

    private void draw() {
        for (Observer o : observers) {
            o.update();
        }
                            //Fixa
        for (Observer o : state.getObservers()) {
            o.update();
        }
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
