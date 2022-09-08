package main;

import gamestates.GameState;
import gamestates.InGameState;
import mapclasses.GameMap;
import sprites.Player;
import sprites.Sprite;
import view.Observer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active GameState
 */
public class Game implements Runnable {
    private Thread gameLoopThread;
    private final int FPS = 120; // FRAMES PER SECOND
    private final int UPS = 200; // UPDATES PER SECOND
    private ArrayList<Observer> observers;
    private GameState state;

    private Player player;
    private GameMap gameMap;

    public Game() throws IOException {
        player = new Player(10, 10, 100);
        gameMap = new GameMap();
        observers = new ArrayList<>();
        state = new InGameState(this);
        startGame();
    }

    /**
     * Returns the instance of the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All sprites
     */
    public ArrayList<Sprite> getTiles() {
        return gameMap.getTiles();
    }

    /**
     * Use to change the current GameState
     * @param state an instance of a class that has implemented the GameState interface
     */
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

    /**
     * Add an observer. Observers will be notified 120 times per second
     * @param observer observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Starts the game
     */
    private void startGame() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    /**
     * Updates the current GameState
     */
    private void update() {
        state.update();
    }

    /**
     * Notifies potential observers
     */
    private void notifyObservers() {
        for (Observer o : observers) {
            o.draw();
        }
    }

    /**
     * Main game loop.
     * Handles logic of when to update the internal game classes and when to notify potential observers
     */
    @Override
    public void run() {

        double timePerRender =  1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        double deltaUpdateTime = 0;
        double deltaDrawTime = 0;
        long currentTime;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastFpsCheck = System.currentTimeMillis();

        while(true) {
            currentTime = System.nanoTime();

            deltaUpdateTime += (currentTime - previousTime) / timePerUpdate;
            deltaDrawTime += (currentTime - previousTime) / timePerRender;
            previousTime = currentTime;

            if (deltaUpdateTime >= 1) {
                update();
                updates++;
                deltaUpdateTime--;
            }

            if (deltaDrawTime >= 1) {
                notifyObservers(); //DRAW
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
