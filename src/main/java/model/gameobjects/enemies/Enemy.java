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
     * Method used to move the enemy towards player.
     *
     */
    private void moveToGoal(double speed) {
        Terrain current = getMapLocation();
        Terrain next = AStar.aStar(current, game.getPlayer().getMapLocation());

        Vector2 nextPos;

        int currentX = (int) current.getPos().getX();
        int currentY = (int) current.getPos().getY();
        if(next != null) {
            nextPos = next.getPos();
            if (currentX < nextPos.getX()) {
                setDirection(EDirection.RIGHT);
                setPosX(getPosX() + speed);
            } else if (currentX > nextPos.getX()) {
                setDirection(EDirection.LEFT);
                setPosX(getPosX() - speed);
            } else if (currentY < nextPos.getY()) {
                setDirection(EDirection.DOWN);
                setPosY(getPosY() + speed);
            } else if (currentY > nextPos.getY()) {
                setDirection(EDirection.UP);
                setPosY(getPosY() - speed);
            }
        }
    }

    @Override
    public void update() {
        moveToGoal(0.5);
    }

}
