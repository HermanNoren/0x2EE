package model.gameobjects.enemies;

import config.Config;
import controllers.EDirection;
import model.gameobjects.Player;
import model.helperclasses.AStar;
import model.Game;
import model.helperclasses.Vector2;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;
import model.gameobjects.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Enemy extends Entity implements IEnemy {

    private int size = Config.SPRITE_SIZE;
    private Game game;
    protected Enemy(int x, int y, Game game){
        super(x, y, game);
        this.game = game;
    }

    /**
     * Method used to move the enemy to target goal.
     *
     */
    public void moveToGoal() {
        Player player = game.getPlayer();
        Terrain target = player.getMapLocation();

        Terrain current = getMapLocation();

        Terrain next = AStar.aStar(current, target);

        assert next != null;
        Vector2 nextPos = next.getPos();

        int currentX = current.getX();
        int currentY = current.getY();

        if (currentX < nextPos.x){
            setDirection(EDirection.RIGHT);
            setPosX(getPosX() +0.5);
        }
        else if(currentX > nextPos.x){
            setDirection(EDirection.LEFT);
            setPosX(getPosX() -0.5);
        }
        else if (currentY < nextPos.y) {
            setDirection(EDirection.DOWN);
            setPosY(getPosY()+0.5);
        }
        else if (currentY > nextPos.y) {
            setDirection(EDirection.UP);
            setPosY(getPosY()-0.5);
        }
    }

    @Override
    public void update() {
        moveToGoal();
    }
}
