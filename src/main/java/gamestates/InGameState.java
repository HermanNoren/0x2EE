package gamestates;

import Collision.CollisionHandler;
import gameobjects.Entity;
import gameobjects.theShop.ShopSprite;
import helperclasses.Vector2;
import main.Game;
import gameobjects.IGameObject;

import gameobjects.Player;
import gameobjects.Projectile;
import gameobjects.enemies.EnemyFactory;
import gameobjects.enemies.IEnemy;
import gameobjects.enemies.NormalEnemyFactory;
import view.panelstates.EPanelState;

import java.util.ArrayList;
import java.util.Map;

/**
 * The IGameState that represents the in-game logic
 */
public class InGameState implements IGameState {
    private ArrayList<IGameObject> sprites;
    private ArrayList<IEnemy> enemies;
    private ArrayList<Projectile> projectiles;
    private final Game game;
    private Player player;
    private ShopSprite shop;
    private final EPanelState stateTag = EPanelState.INGAME;

    public InGameState() {
        this.game = Game.getInstance();
        sprites = new ArrayList<>();
        enemies = game.getEnemies();
        projectiles = game.getProjectiles();
        this.shop = game.getShop();
        this.player = game.getPlayer();
        EnemyFactory enemyFactory= new NormalEnemyFactory();
        enemies.add(enemyFactory.createEnemy());
        sprites.add(game.getPlayer());
        sprites.addAll(game.getTerrainBorder());
        sprites.add(game.getShop());
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

        if (game.getSpacePressed()) {
            game.resetSpacePressed();
            player.shoot(projectiles);
        }

        for (IGameObject sprite : sprites) {
            sprite.update();
        }

        for(IEnemy enemy : enemies){
            enemy.update();
            //Check if enemy is close enough to damage player, could be done somewhere else also.
            if (CollisionHandler.testCollision(player, (Entity) enemy)) {
                player.damageTaken(1);
            }

        }
        for (Projectile p : projectiles) {
            p.update();
        }

        CollisionHandler.seeIfPlayerIsOutsideBorder(player);
        
        }
    }

