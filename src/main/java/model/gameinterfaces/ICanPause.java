package model.gameinterfaces;

/**
 * Used in game to pause and resume the game loop.
 */
public interface ICanPause {
    /**
     * Returns a boolean regarding if the game is paused, true equals paused.
     */
    boolean isPaused();
    /**
     * Called in game to pause it.
     */
    void pause();

    /**
     * Called in game to resume it.
     */
    void resume();
}
