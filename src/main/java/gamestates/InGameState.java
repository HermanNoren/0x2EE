package gamestates;

import main.Game;
import sprites.ISprite;

import sprites.Player;
import sprites.enemies.Enemy;
import sprites.enemies.EnemyFactory;
import sprites.enemies.IEnemy;
import sprites.enemies.NormalEnemyFactory;
import view.panelstates.EPanelState;

import java.util.ArrayList;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<ISprite> sprites;
    private ArrayList<IEnemy> enemies;
    private Game game;
    private Player player;
    private final EPanelState stateTag = EPanelState.INGAME;

    public InGameState(Game game) {
        this.game = game;
        sprites = new ArrayList<>();
        enemies = game.getEnemies();
        this.player = game.getPlayer();
        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy());
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTiles());
    }



    /**
     * Returns the specific state tag
     * @return stateTag
     */
    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    /**
     * Updates all the in-game objects
     */
    @Override
    public void update() {
        if (game.getEscapePressed()) {
            game.resetEscapePressed();
            game.setState(new PauseState(game));
        }

        for (ISprite sprite : sprites) {
            sprite.update();
        }
    }
}
