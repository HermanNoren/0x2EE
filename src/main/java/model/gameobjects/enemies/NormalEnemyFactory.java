package model.gameobjects.enemies;

import model.Game;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.mapclasses.GameMap;
import model.mapclasses.Terrain;

import java.util.List;
import java.util.Random;

public class NormalEnemyFactory extends EnemyFactory{
    @Override
    public Enemy createEnemy(Entity targetEntity, Terrain[][] coordinates, Random rand) {

        NormalEnemy normalEnemy = new NormalEnemy(rand.nextInt(480),rand.nextInt(480), targetEntity, coordinates);
        return normalEnemy;
    }
}
