package main;

import gamestates.GameState;
import gamestates.GameStateWithPlayer;
import gamestates.InGameState;
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

    public Game() throws IOException {
        observers = new ArrayList<>();
        state = new InGameState();
        startGame();
    }

    /**
     * Returns the player if current GameState has a player, else throws an exception
     * @return Player
     */
    public Player getPlayer() {
        try {
            if (state instanceof GameStateWithPlayer) {
                return ((GameStateWithPlayer) state).getPlayer();
            }
            else {
                throw new Exception("Current GameState has no player");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sprite> getTiles() {
        try {
            if (state instanceof GameStateWithPlayer) {
                return ((GameStateWithPlayer) state).getTiles();
            }
            else {
                throw new Exception("Current GameState has no player");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
     * Returns an ArrayList containing all the sprites in the current GameState
     * @return ArrayList containing Sprites
     */
    public ArrayList<Sprite> getSprites() {
        return state.getSprites();
    }

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
