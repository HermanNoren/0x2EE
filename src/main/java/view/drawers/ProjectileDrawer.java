package view.drawers;

import model.Game;
import model.gameobjects.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectileDrawer implements IDrawer {

    private Game game;

    private List<Projectile> projectiles;

    public ProjectileDrawer(Game game) {
        this.game = game;
    }

    @Override
    public void draw(Graphics2D g) {
        projectiles = game.getProjectiles();
        for (Projectile p : projectiles) {
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(p.getPos(), p.getWidth(), p.getHeight());
            g.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}
