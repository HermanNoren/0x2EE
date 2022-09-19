package gamestates;

import Collision.CollisionHandler;
import main.Game;
import sprites.ISprite;

import sprites.Player;
import sprites.enemies.EnemyFactory;
import sprites.enemies.IEnemy;
import sprites.enemies.NormalEnemyFactory;
import view.panelstates.EPanelState;

import java.io.PrintStream;
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

    public InGameState() {
        this.game = Game.getInstance();
        sprites = new ArrayList<>();
        enemies = game.getEnemies();
        this.player = game.getPlayer();
        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy());
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTerrainBorder());
    }

    /**
     * Returns the specific state tag
     * @return stateTag
     */
    @Override
    public EPanelState getStateTag() {
        return stateTag;
    }

    @Override
    public void updateButtons() {
    }

    /**
     * Updates all the in-game objects
     * **Implement collision logic here**
     * Maybe better to implement the border here to make it check faster although this approach is more OOP.
     */
    @Override
    public void update() {

        /* GAME OVER
        if (player.getHealth() < 1){
            game.setState(new MainMenuState());
        }

         */

        if (game.getEscapePressed()) {
            game.resetEscapePressed();
            game.setState(new PauseState());
        }

        for (ISprite sprite : sprites) {
            sprite.update();
        }
        for(IEnemy enemy : enemies){
            enemy.update();
            //Check if enemy is close enough to damage player, could be done somewhere else also.
        }

        CollisionHandler.seeIfPlayerIsOutsideBorder(player);
        
        }
    }
}
