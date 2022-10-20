package view.drawers;

import controllers.sound.SoundEffect;
import model.gameinterfaces.IHasProjectiles;
import model.gameobjects.Projectile;
import model.gameobjects.EDirection;
import view.ImageHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to draw Projectiles
 */
public class ProjectileDrawer implements IImageIteratorDrawer{

    private IHasProjectiles game;
    private List<Projectile> projectiles;
    private Map<EDirection, Map<String, BufferedImage>> projectileImgs;
    private List<String> imgsTypes;
    private List<EDirection> directions;
    private int index = 0;



    public ProjectileDrawer(IHasProjectiles game) {
        this.game = game;
        projectileImgs = new HashMap<>();
        imgsTypes = new ArrayList<>();
        imgsTypes.add("bullet");
        directions = new ArrayList<>();
        directions.add(EDirection.UP);
        directions.add(EDirection.LEFT);
        directions.add(EDirection.DOWN);
        directions.add(EDirection.RIGHT);

        initImages();
    }

    private void initImages(){
        projectileImgs = ImageHandler.getImgsWithDirections(2, imgsTypes, directions);
    }

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
