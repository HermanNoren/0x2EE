package view.drawers;

import model.gameobjects.Projectile;

import java.awt.*;
import java.util.ArrayList;

public class ProjectileDrawer implements IDrawer {
    private final ArrayList<Projectile> projectiles;

    public ProjectileDrawer(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    @Override
    public void draw(Graphics2D g) {
        for (Projectile p : projectiles) {
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(p.getPos(), p.getWidth(), p.getHeight());
            g.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}
