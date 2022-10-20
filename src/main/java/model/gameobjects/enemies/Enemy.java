package model.gameobjects.enemies;

import model.gameobjects.EDirection;
import model.Vector2;
import model.gameobjects.IFocusableObject;
import model.mapclasses.Tile;
import model.gameobjects.Entity;

/**
 * Representation of the enemy class, used to glue together logic
 * from AStar, implements damage that damages the player when player is hit.
 */
public abstract class Enemy extends Entity implements IEnemy, IFocusableObject {
    private double movementSpeed;
    private final Entity targetEntity;
    protected Enemy(int x, int y, int damage, int killReward, Tile[][] coordinates, Entity targetEntity){
        super(x, y, coordinates);
        this.targetEntity = targetEntity;
        setMovementSpeed(1.5);
    }
    @Override
    public Entity getTargetEntity(){
        return this.targetEntity;
    }

    /**
     * Method used to move the enemy towards player.
     */
    private void moveToGoal(double dt) {
        Tile current = getMapLocation();
        Tile goal = targetEntity.getMapLocation();

        Tile next = AStar.aStar(current, goal);

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

    @Override
    public void damageTaken(int damage) {
        setHealth(getHealth()-damage);
    }

    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
