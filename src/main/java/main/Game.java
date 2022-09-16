package main;

import gamestates.*;
import mapclasses.GameMap;
import sprites.ISprite;
import sprites.Player;
import buttons.GameButton;
import buttons.buttonactions.BackButtonAction;
import buttons.buttonactions.EmptyButtonAction;
import buttons.buttonactions.StartGameButtonAction;
import sprites.enemies.IEnemy;
import view.IObserver;
import view.panelstates.EPanelState;

import java.util.ArrayList;

/**
 * This class contains the main game loop.
 * With help of the main game loop it delegates work to the active IGameState
 */
public class Game implements Runnable {
    private Thread gameLoopThread;
    private final int FPS = 120; // FRAMES PER SECOND
    private final int UPS = 200; // UPDATES PER SECOND
    private ArrayList<IObserver> observers;
    private IGameState state;
    private Player player;
    private ArrayList<IEnemy> enemies;
    private GameMap gameMap;

    private ArrayList<GameButton> mainMenuButtons, backButtons, pauseButtons;

    private boolean wPressed, aPressed, sPressed, dPressed, enterPressed, escapePressed;

    private boolean stateChangedFlag;

    private Game() {}

    private static Game game;
    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }return game;
    }

    public void createGame(){
        player = new Player(10, 10, 0.5, 100);
        enemies = new ArrayList<>();
        gameMap = new GameMap();

        initMainMenuButtons();
        initBackButtons();
        initPauseButtons();

        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        enterPressed = false;
        escapePressed = false;

        stateChangedFlag = false;

        state = new MainMenuState(this);
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
    public ArrayList<IEnemy> getEnemies(){
        return enemies;
    }

    /**
     * Returns an ArrayList containing all the tiles in the Game Map.
     * @return  All Game Map Tiles
     */
    public ArrayList<ISprite> getTiles() {
        return gameMap.getTiles();
    }

    /**
     * Returns an ArrayList containing all the buttons for the main menu.
     * @return  Main Menu Buttons
     */
    public ArrayList<GameButton> getMainMenuButtons() {
        return mainMenuButtons;
    }

    public ArrayList<GameButton> getBackButtons(){return backButtons;}

    public ArrayList<GameButton> getPauseButtons(){
        return pauseButtons;
    }

    /**
     * Used as a way for outside components to tell Game if the W key is pressed.
     */
    public void setWPressed() {
        wPressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the W key is released.
     */
    public void resetWPressed() {
        wPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the S key is pressed.
     */
    public void setSPressed() {
        sPressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the S key is released.
     */
    public void resetSPressed() {
        sPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the Enter key is pressed.
     */
    public void setEnterPressed() {
        enterPressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the Enter key is released.
     */
    public void resetEnterPressed() {
        enterPressed = false;
    }

    /**
     * Used as a way for outside components to tell Game if the Escape key is pressed.
     */
    public void setEscapePressed() {
        escapePressed = true;
    }

    /**
     * Used as a way for outside components to tell Game if the Escape key is released.
     */
    public void resetEscapePressed() {
        escapePressed = false;
    }

    /**
     * Used as a way for objects inside to read whether the W key is pressed
     * @return wPressed
     */
    public boolean getWPressed() {
        return wPressed;
    }

    public boolean getSPressed() {
        return sPressed;
    }

    public boolean getEnterPressed() {
        return enterPressed;
    }

    public boolean getEscapePressed() {
        return escapePressed;
    }

    public boolean readStateChangedFlag() {
        return stateChangedFlag;
    }

    public void resetStateChangedFlag() {
        stateChangedFlag = false;
    }

    /**
     * Use to change the current IGameState
     * @param state an instance of a class that has implemented the IGameState interface
     */
    public void setState(IGameState state) {
        this.state = state;
        stateChangedFlag = true;
    }

    /**
     * Returns a string containing a unique tag used for identifying the different game states from outside sources.
     * @return StateTag
     */
    public EPanelState getStateTag() {
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
    public void addObserver(IObserver observer) {
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
     * Updates the current IGameState
     */
    private void update() {
        state.update();
    }

    /**
     * Notifies potential observers
     */
    private void notifyObservers() {
        for (IObserver o : observers) {
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
        GameButton mainMenuButton1 = new GameButton("PLAY", 325, 200, new StartGameButtonAction(this));
        GameButton mainMenuButton2 = new GameButton("HIGHSCORES", 325, 300, new EmptyButtonAction());
        GameButton mainMenuButton3 = new GameButton("HOW TO PLAY", 325, 400, new EmptyButtonAction());
        GameButton mainMenuButton4 = new GameButton("QUIT", 325, 500, new EmptyButtonAction());
        mainMenuButtons = new ArrayList<>();
        mainMenuButtons.add(mainMenuButton1);
        mainMenuButtons.add(mainMenuButton2);
        mainMenuButtons.add(mainMenuButton3);
        mainMenuButtons.add(mainMenuButton4);
    }

    private void initBackButtons(){
        GameButton backButton1 = new GameButton("BACK", 325, 650, new BackButtonAction(this));
        backButton1.setIsSelected(true);
        backButtons = new ArrayList<>();
        backButtons.add(backButton1);
    }

    private void initPauseButtons(){
        GameButton pauseButton1 = new GameButton("RESUME", 325, 200, new StartGameButtonAction(this));
        GameButton pauseButton2 = new GameButton("RESTART", 325, 300, new StartGameButtonAction(this));
        GameButton pauseButton3 = new GameButton("MAIN MENU", 325, 400, new StartGameButtonAction(this));
        pauseButtons = new ArrayList<>();
        pauseButtons.add(pauseButton1);
        pauseButtons.add(pauseButton2);
        pauseButtons.add(pauseButton3);

    }

}
