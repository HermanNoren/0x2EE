package main;

import gamestates.GameState;
import mapclasses.GameMap;
import gamestates.MenuTest;
import sprites.Player;
import sprites.Sprite;
import sprites.buttons.GameButton;
import sprites.buttons.buttonactions.EmptyButtonAction;
import sprites.buttons.buttonactions.StartGameButtonAction;
import view.Observer;

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
    private GameButton mainMenuButton1;
    private GameButton mainMenuButton2;
    private GameButton mainMenuButton3;
    private ArrayList<GameButton> mainMenuButtons;
    private boolean wPressed;
    private boolean aPressed;
    private boolean sPressed;
    private boolean dPressed;
    private boolean enterPressed;


    private boolean stateChangedFlag;

    public Game() {
        player = new Player(10, 10, 100);
        gameMap = new GameMap();

        initMainMenuButtons();

        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        enterPressed = false;

        stateChangedFlag = false;

        state = new MenuTest(this);
        observers = new ArrayList<>();

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
     * @return  All Game Map Tiles
     */
    public ArrayList<Sprite> getTiles() {
        return gameMap.getTiles();
    }

    /**
     * Returns an ArrayList containing all the buttons for the main menu.
     * @return  Main Menu Buttons
     */
    public ArrayList<GameButton> getMainMenuButtons() {
        return mainMenuButtons;
    }

    /**
     * Used as a way for outside controllers to tell the game whether the W key is pressed or released.
     * @param value True if W pressed, else False
     */
    public void setWPressed(boolean value) {
        wPressed = value;
    }

    /**
     * Used as a way for outside controllers to tell the game whether the A key is pressed or released.
     * @param value True if A pressed, else False
     */
    public void setAPressed(boolean value) {
        aPressed = value;
    }

    /**
     * Used as a way for outside controllers to tell the game whether the S key is pressed or released.
     * @param value True if S pressed, else False
     */
    public void setSPressed(boolean value) {
        sPressed = value;
    }

    /**
     * Used as a way for outside controllers to tell the game whether the D key is pressed or released.
     * @param value True if D pressed, else False
     */
    public void setDPressed(boolean value) {
        dPressed = value;
    }

    /**
     * Used as a way for outside controllers to tell the game whether the Enter key is pressed or released.
     * @param value True if Enter pressed, else False
     */
    public void setEnterPressed(boolean value) {
        enterPressed = value;
    }

    /**
     * Used as a way for objects inside to read whether the W key is pressed
     * @return
     */
    public boolean getWPressed() {
        return wPressed;
    }

    public boolean getAPressed() {
        return aPressed;
    }

    public boolean getSPressed() {
        return sPressed;
    }

    public boolean getDPressed() {
        return dPressed;
    }

    public boolean getEnterPressed() {
        return enterPressed;
    }

    public boolean readStateChangedFlag() {
        return stateChangedFlag;
    }

    public void resetStateChangedFlag() {
        stateChangedFlag = false;
    }

    /**
     * Use to change the current GameState
     * @param state an instance of a class that has implemented the GameState interface
     */
    public void setState(GameState state) {
        this.state = state;
        stateChangedFlag = true;
    }

    /**
     * Returns a string containing a unique tag used for identifying the different game states from outside sources.
     * @return StateTag
     */
    public String getStateTag() {
        return state.getStateTag();
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

    /**
     * Initializes the buttons used in the main menu and stores them in an ArrayList
     */
    private void initMainMenuButtons() {
        mainMenuButton1 = new GameButton("Start Game", 50, 50, new StartGameButtonAction(this));
        mainMenuButton2 = new GameButton("Knapp 2", 50, 150, new EmptyButtonAction());
        mainMenuButton3 = new GameButton("Knapp 3", 50, 250, new EmptyButtonAction());
        mainMenuButtons = new ArrayList<>();
        mainMenuButtons.add(mainMenuButton1);
        mainMenuButtons.add(mainMenuButton2);
        mainMenuButtons.add(mainMenuButton3);
    }
}
