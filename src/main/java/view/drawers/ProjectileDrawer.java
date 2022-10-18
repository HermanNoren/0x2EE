package view.drawers;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ProjectileDrawer implements IDrawer {

    private IHasProjectiles game;

    private List<Projectile> projectiles;

    private BufferedImage up1, up2, left1, left2, down1, down2, right1, right2;

    public ProjectileDrawer(IHasProjectiles game) {
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
