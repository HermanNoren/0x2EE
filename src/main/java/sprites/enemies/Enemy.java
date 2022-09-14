package sprites.enemies;

import controllers.EDirection;

import java.util.Random;

public abstract class Enemy extends sprites.Entity {

    int damage = 3;

    private EEnemyType enemyType;

    public Enemy(int position_x, int position_y, int vel,  int health, EEnemyType enemyType){
        super(position_x, position_y, vel, health);
        this.enemyType = enemyType;

    }



    public int damageDelt() {
        return 0;
    }


    public void damageTaken(int damage) {

    }

    private int getRandomNr(){
        Random random = new Random();
        int upperBound = 200;
        return random.nextInt(upperBound);
    }

    private void setRandomDirection(){
        if(getRandomNr() == 0){
            setDirection(EDirection.UP);
        }if(getRandomNr() == 1){
            setDirection(EDirection.LEFT);
        }if(getRandomNr() == 2) {
            setDirection(EDirection.DOWN);
        }if (getRandomNr() == 3){
            setDirection(EDirection.RIGHT);
        }if (getRandomNr() == 4){
            setDirection(EDirection.NOT_MOVING);
        }
    }
    public void moveEnemy(){
        setRandomDirection();
        updatePos();
    }
}
