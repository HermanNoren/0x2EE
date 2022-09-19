package view.drawers;

import sprites.Bullet;
import view.Camera;

import java.awt.*;
import java.util.ArrayList;

public class BulletDrawer implements IDrawer {
    private Bullet bullet;
    private Camera camera;

    public BulletDrawer(Bullet bullet, Camera camera) {
        this.bullet = bullet;
        this.camera = camera;
    }

    public void changeBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void draw(Graphics2D g) {
        ArrayList<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(bullet.getPos(), bullet.getWidth(), bullet.getHeight(), camera);
        g.fillRect(drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3));
    }
}
