package model.gameobjects.enemies;

import config.Config;
import controllers.EDirection;
import model.helperclasses.AStar;
import model.Game;
import model.helperclasses.Vector2;
import model.mapclasses.Terrain;
import model.gameobjects.Entity;

public abstract class Enemy extends Entity implements IEnemy {
    private int size = Config.SPRITE_SIZE;
    private double movementSpeed;
    private Terrain[][] coordinates;
    private Entity targetEntity;
    protected Enemy(int x, int y, Entity targetEntity, Terrain[][] coordinates){
        super(x, y, coordinates);
        this.targetEntity = targetEntity;
        this.coordinates = coordinates;
        movementSpeed = 0.5;
    }

    /**
     * Method used to move the enemy towards player.
     */
    private void moveToGoal(double dt) {
        Terrain current = getMapLocation(coordinates);
        Terrain goal = targetEntity.getMapLocation(coordinates);

        Terrain next = AStar.aStar(current, goal);

        Vector2 nextPos;

        double currentX = current.getCenter().getX();
        double currentY = current.getCenter().getY();

        if(next != null) {
            nextPos = next.getCenter();
            double nextX = nextPos.getX();
            double nextY = nextPos.getY();
            if (currentX < nextX) {
                setDirection(EDirection.RIGHT);
                setPosX(getPosX() + movementSpeed * dt);
            }else if (currentX > nextX) {
                setDirection(EDirection.LEFT);
                setPosX(getPosX() - movementSpeed * dt);

            }else if (currentY < nextY) {
                setDirection(EDirection.DOWN);
                setPosY(getPosY() + movementSpeed * dt);

            }else if (currentY > nextY) {
                setDirection(EDirection.UP);
                setPosY(getPosY() - movementSpeed * dt);

            }
        }
    }
    @Override
    public void update(double dt) {
        moveToGoal(dt);
    }
}
