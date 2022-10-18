package view.drawers;

import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.IGameObject;
import model.gameobjects.IProjectile;
import model.gameobjects.Projectile;
import model.helperclasses.EDirection;
import view.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectileDrawer implements IImageIteratorDrawer{

    private IHasProjectiles game;
    private List<Projectile> projectiles;
    private Map<EDirection, Map<String, BufferedImage>> projectileImgs;
    private List<String> imgsTypes;



    public ProjectileDrawer(IHasProjectiles game) {
        this.game = game;
        projectileImgs = new HashMap<>();
        imgsTypes = new ArrayList<>();
        imgsTypes.add("bullet");
        initImgs();
    }
    private void initImgs(){
        ImageHandler.setImgsWithDirections(2, projectileImgs, imgsTypes, EDirection.up);
        ImageHandler.setImgsWithDirections(2, projectileImgs, imgsTypes, EDirection.left);
        ImageHandler.setImgsWithDirections(2, projectileImgs, imgsTypes, EDirection.down);
        ImageHandler.setImgsWithDirections(2, projectileImgs, imgsTypes, EDirection.right);

    }


    private int index = 0;
    @Override
    public void draw(Graphics2D g) {
        projectiles = game.getProjectiles();
        BufferedImage img;
        for(Projectile projectile : projectiles) {
            img = projectileImgs.get(projectile.getDirection()).get("bullet"+index);
            List<Integer> drawInformation = DrawerHelper.calculateDrawingInformation(projectile.getPos(), projectile.getWidth(), projectile.getWidth());
            g.drawImage(img, drawInformation.get(0), drawInformation.get(1), drawInformation.get(2), drawInformation.get(3), null);
        }
    }

    @Override
    public void switchImage() {
        index ++;
        index%=2;
    }
}
