package gamestates;

import main.Game;
import sprites.Entity;
import sprites.ISprite;

import sprites.Player;
import sprites.enemies.Enemy;
import view.panelstates.EStateTag;

import java.util.ArrayList;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<ISprite> sprites;

    private Game game;
    private Player player;
    private final EStateTag stateTag = EStateTag.INGAME;

    public InGameState(Game game) {
        this.game = game;
        sprites = new ArrayList<>();
        this.player = game.getPlayer();
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTiles());
    }



    /**
     * Returns the specific state tag
     * @return stateTag
     */
    @Override
    public EStateTag getStateTag() {
        return stateTag;
    }

    /**
     * Updates all the in-game objects
     */
    @Override
    public void update() {
        player.movePlayer(game);
        for(Enemy enemy : game.getEnemies()){
            enemy.moveEnemy();
        }
        for (ISprite sprite : sprites) {
            sprite.update();
        }
    }
}
