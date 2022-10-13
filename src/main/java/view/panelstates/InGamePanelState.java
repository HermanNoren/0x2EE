package view.panelstates;

import config.Config;
import controllers.*;
import model.Game;
import view.Camera;
import view.HUD;
import view.MainPanel;
import view.drawers.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InGamePanelState implements IPanelState {

    private Game game;
    private HUD hud;
    /**
     * Important to know in the constructor in which order the drawable objects are put in as
     * a drawing hierarchy is created (which object is drawn over whom).
     * The first object added to the Drawers ArrayList will be seen as under everything added after it.
     * */
    private List<IDrawer> drawers;

    private PlayerDrawer playerDrawer;
    private EnemyDrawer enemyDrawer;
    private ItemDrawer itemDrawer;

    private ImageSwitcherController imageSwitcherController;
    private final Camera camera;
    private final ArrayList<KeyListener> keyListeners;
    private MainPanel mainPanel;

    public InGamePanelState(MainPanel mainPanel, Game game) {
        this.game = game;
        this.mainPanel = mainPanel;
        keyListeners = new ArrayList<>();
        keyListeners.add(new PlayerController(game.getPlayer()));
        keyListeners.add(new WeaponController(game));
        keyListeners.add(new KeyClickedController(game, this));
        hud = new HUD(game.getPlayer());
        camera = Camera.getInstance();
        camera.setFocusedObject(game.getPlayer());
        camera.setBorderLimit(0, game.getMapSize() * Config.TILE_SIZE,
                              0, game.getMapSize() * Config.TILE_SIZE);
        keyListeners.add(new CameraController());

        playerDrawer = new PlayerDrawer(game.getPlayer());
        enemyDrawer = new EnemyDrawer(game.getEnemies(), "boss");
        itemDrawer = new ItemDrawer(game.getItems());

        drawers = new ArrayList<>();
        drawers.add(new MapDrawer(game.getGameMap().getGameMapCoordinates()));
        drawers.add(new ProjectileDrawer(game));
        drawers.add(new ShopDrawer(game.getShop()));
        drawers.add(playerDrawer);
        drawers.add(enemyDrawer);
        drawers.add(itemDrawer);
        drawers.add(new InteractShopTextDrawer(game.getShop()));

        imageSwitcherController = new ImageSwitcherController(250);
        imageSwitcherController.addImageDrawer(playerDrawer);
        imageSwitcherController.addImageDrawer(enemyDrawer);
        imageSwitcherController.addImageDrawer(itemDrawer);
        imageSwitcherController.start();
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
    }

    @Override
    public List<KeyListener> getKeyListeners() {
        return keyListeners;
    }
}
