package view.drawers;

import gameobjects.Projectile;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

public class ProjectileDrawer implements IDrawer {
    private final ArrayList<Projectile> projectiles;
    private final Camera camera;

    public ProjectileDrawer(ArrayList<Projectile> projectiles, Camera camera) {
        this.projectiles = projectiles;
        this.camera = camera;
    }

    @Override
    public void draw(Graphics2D g) {
        for (Projectile p : projectiles) {
            ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(p.getPos(), p.getWidth(), p.getHeight(), camera);
            g.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
        }
    }
}
