import model.Game;
import model.gameinterfaces.*;
import model.gameobjects.EDirection;
import model.gameobjects.ItemSpawner.IItem;
import model.gameobjects.Player;
import model.gameobjects.Projectile;
import model.gameobjects.Shop;
import model.gameobjects.enemies.Enemy;
import model.helperclasses.HighscoreHandler;
import model.helperclasses.TransactionHandler;
import model.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Game and all its interfaces
 */
public class GameTest {
    Game game;

    @BeforeEach
    void init(){
        game = new Game();
    }

    @Test
    void test_if_game_can_pause(){
        ICanPause game = this.game;
        boolean startUnPaused = !game.isPaused();
        game.pause();
        boolean canPause = game.isPaused();
        game.resume();
        boolean canResume = !game.isPaused();
        assertTrue(startUnPaused && canPause && canResume);
    }

    @Test
    void test_if_game_has_enemies(){
        IHasEnemies game = this.game;
        List<Enemy> enemyList = new ArrayList<>();
        assertEquals(game.getEnemies().getClass(), enemyList.getClass());
    }

    @Test
    void test_if_gmae_can_create_game_and_get_player(){
        ICreateGame creatorGame = this.game;
        IHasPlayer  gameWithPlayer = this.game;
        Player player = gameWithPlayer.getPlayer();
        creatorGame.newGameRound();
        assertNotSame(player, gameWithPlayer.getPlayer());
    }

    @Test
    void test_if_gameMap_methods_work(){
        IHasGameMap game = this.game;
        GameMap gameMap = new GameMap(2,2, false);
        assertEquals(game.getGameMap().getClass(), gameMap.getClass());
    }

    @Test
    void test_if_highscore_interface_works(){
        // TODO
    }

    @Test
    void test_if_game_has_items(){
        List<IItem> items = new ArrayList<>();
        assertEquals(game.getItems().getClass(), items.getClass());
    }

    @Test
    void test_if_game_can_call_player_methods(){
        assertTrue(!game.isTopFive() && !game.isPlayerDead());
    }

    @Test
    void test_if_game_has_projectiles(){
        game.addProjectile(new Projectile(new Vector2(1,1), EDirection.up));
        List<Projectile> projectiles = game.getProjectiles();
        assertEquals(1, projectiles.size());
    }

    @Test
    void test_if_player_starts_on_shop(){
        assertFalse(game.playerOnShop());
    }

    @Test
    void test_if_shop_and_transaction_handler_is_reachable(){
        Shop shop = new Shop(1,1);
        TransactionHandler transactionHandler = new TransactionHandler(new Player(1, 1, this.game.getGameMap().getGameMapCoordinates()));
        assertTrue(shop.getClass() == game.getShop().getClass() && game.getShopTransaction().getClass() == transactionHandler.getClass());
    }

    @Test
    void is_map_size_25(){
        assertEquals(25, game.getMapSize());
    }

    @Test
    void can_get_highscore_name(){
        List<String> stringList = new ArrayList<>();
        assertEquals(stringList.getClass(), game.getHighscoreName().getClass());
    }

    @Test
    void can_reach_top_five(){
        HighscoreHandler handler = new HighscoreHandler();
        List<String> highscores = handler.getHighscoreList();
        handler.rollBackFile(new ArrayList<>());
        handler.saveHighscore("HEJSAN", 100);
        handler.saveHighscore("HEJSAN", 200);
        handler.saveHighscore("HEJSAN", 300);
        handler.saveHighscore("HEJSAN", 400);
        handler.saveHighscore("HEJSAN", 500);
        game.getPlayer().addScore(600);
        game.updateHighscoreList();
        assertTrue(game.isTopFive() && Objects.equals(handler.getHighscoreList().get(1), "HEJSAN:500"));
        handler.rollBackFile(highscores);
    }

    @Test
    void is_top_five_if_not_5_scores(){
        HighscoreHandler handler = new HighscoreHandler();
        List<String> highscores = handler.getHighscoreList();
        handler.rollBackFile(new ArrayList<>());
        game.getPlayer().addScore(1);
        assertTrue(game.isTopFive());
        handler.rollBackFile(highscores);
    }

    @Test
    void test_if_highscore_name_is_writable(){
        game.updateName("R");
        game.updateName("O");
        game.deleteLetter();
        assertEquals("[R]", game.getHighscoreName().toString());
    }

//    @Test
//    void test_if_update_updates(){
//        double pos = game.getPlayer().getPosX();
//        game.getPlayer().setDirection(EDirection.right);
//        game.makePlayerShoot();
//        game.getPlayer().setDirection(EDirection.down);
//        game.makePlayerShoot();
//        game.getPlayer().setDirection(EDirection.left);
//        game.makePlayerShoot();
//        game.getPlayer().setDirection(EDirection.up);
//        game.makePlayerShoot();
//        game.getPlayer().setDirection(EDirection.right);
//        game.getPlayer().setHealth(0);
//        game.update(40);
//        assertTrue(pos == game.getPlayer().getPosX() && game.isPlayerDead());
//    }


}
