package view.panelstates;

import config.Config;
import controllers.*;
import model.gameinterfaces.IGame;
import view.Camera;
import view.HUD;
import view.IChangeableStatePanel;
import view.drawers.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the view for the in-game state.
 * @author Arthur Alexandersson, Gustav Gille, Herman Noren, Kasper Lundgren, Rickard Leksell
 */
public class InGamePanelState implements IPanelState {

    private IGame game;
    private HUD hud;
    /**
     * Important to know in the constructor in which order the drawable objects are put in as
     * a drawing hierarchy is created (which object is drawn over whom).
     * The first object added to the Drawers ArrayList will be seen as under everything added after it.
     * */
    private List<IDrawer> drawers;

    private IImageIteratorDrawer playerDrawer, enemyDrawer, itemDrawer, mapDrawer;

    private ImageSwitcherController characterImageSwitcherController, tileImageSwitcherController;
    private SpawnTimerController spawnTimerController;

    private BossIntroductionController bossIntroductionController;
    private final Camera camera;
    private final List<KeyListener> keyListeners;
    private IChangeableStatePanel mainPanel;

    public InGamePanelState(IChangeableStatePanel mainPanel, IGame game) {
        this.game = game;
        this.mainPanel = mainPanel;

        spawnTimerController = new SpawnTimerController(game, 5000);
        spawnTimerController.run();

        bossIntroductionController = new BossIntroductionController(game);

        hud = new HUD(game.getPlayer());
        camera = Camera.getInstance();
        camera.setFocusedObject(game.getPlayer());
        camera.setBorderLimit(0, game.getMapSize() * Config.TILE_SIZE,
                              0, game.getMapSize() * Config.TILE_SIZE);

        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game.getPlayer()));
        keyListeners.add(new WeaponController(game));
        keyListeners.add(new InGameKeyController(game, this));

        playerDrawer = new PlayerDrawer(game.getPlayer());
        enemyDrawer = new EnemyDrawer(game);
        itemDrawer = new ItemDrawer(game);
        mapDrawer = new MapDrawer(game.getGameMap().getGameMapCoordinates());

        drawers = new ArrayList<>();
        drawers.add(mapDrawer);
        drawers.add(new ProjectileDrawer(game));
        drawers.add(new ShopDrawer(game.getShop()));
        drawers.add(playerDrawer);
        drawers.add(enemyDrawer);
        drawers.add(itemDrawer);
        drawers.add(new InteractShopTextDrawer(game.getShop()));

        characterImageSwitcherController = new ImageSwitcherController(200);

        characterImageSwitcherController.addImageDrawer(playerDrawer);
        characterImageSwitcherController.addImageDrawer(enemyDrawer);
        characterImageSwitcherController.addImageDrawer(itemDrawer);
        characterImageSwitcherController.start();

        tileImageSwitcherController = new ImageSwitcherController(400);
        tileImageSwitcherController.addImageDrawer(mapDrawer);
        tileImageSwitcherController.start();
    }


    @Override
    public void draw(Graphics2D g) {

        if (game.isPlayerDead()) {
            if (game.isTopFive()) {
                changePanelState(EPanelState.NEWHIGHSCORE);
            }else{
                changePanelState(EPanelState.GAMEOVER);
            }
        } else {

            bossIntroductionController.listenForBossSpawn();

            camera.update();
            for (IDrawer drawer : drawers) {
                drawer.draw(g);
            }
           hud.update(g);
        }
    }

    @Override
    public void changePanelState(EPanelState panelState) {
        mainPanel.changePanelState(panelState);
        spawnTimerController.pause();
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
