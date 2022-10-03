package model.helperclasses.collision;

import config.Config;
import controllers.EDirection;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.mapclasses.Terrain;

import java.util.*;

public class CollisionHandler {


    /**
     * Method for calculating if a game object is colliding with another game object
     * @param object1 first object to compare
     * @param object2 second object to compare
     * @return true if objects are colliding, else false
     */
    public static boolean testCollision(IGameObject object1, IGameObject object2) {
        return
                object1.getPos().getX() < object2.getPos().getX() + object2.getWidth()
                && object1.getPos().getX() + object1.getWidth() > object2.getPos().getX()
                && object1.getPos().getY() < object2.getPos().getY() + object2.getHeight()
                && object1.getHeight() + object1.getPos().getY() > object2.getPos().getX();
    }

    /**
     * Method for providing which specific terrain pieces an object is colliding with
     * @param object Object to test
     * @param terrain Matrix containing all the terrain tiles.
     * @return A list containing the terrain pieces that is being collided with
     */
    public static List<Terrain> getSpecificTerrainCollisions(IGameObject object, Terrain[][] terrain) {
        List<Terrain> collidedTerrain = new ArrayList<>();

        Vector2 objectPos = object.getPos();
        int left = (int) (objectPos.getX() / object.getWidth() - 1);
        int right = (int) (objectPos.getX() / object.getWidth() + 1);
        int up = (int) (objectPos.getY() / object.getHeight() - 1);
        int down = (int) (objectPos.getY() / object.getHeight() + 1);

        if (left < 0) {
            left = 0;
        }
        if (up < 0) {
            up = 0;
        }

        for (int col = left; col < right && col < terrain.length; col++) {
            for (int row = up; row < down && row < terrain[0].length; row++) {
                Terrain t = terrain[col][row];
                System.out.println(t.getX());
                if (!t.isPassable()) {
                    if (testCollision(object, t)) {
                        collidedTerrain.add(t);
                    }
                }
            }
        }
        return collidedTerrain;
    }

    /**
     * Method for calculating in which direction a collision is happening.
     * @param object1 Object to which is colliding with a direction
     * @param object2 Object which the first object is colliding into.
     * @param axis The desired axis to test. If desired axis is X_AXIS the method will test if collision is happening
     *             to the right or to the left. If desired axis is Y_AXIS the method will test if collision is happening
     *             at the top or at the bottom.
     * @return The method returns a map containing 4 keys; 'top', 'bottom', 'right', 'left'. Use these keys to get the
     * boolean value of the direction. Example: map.get("top") will be true if collision is at the top.
     */
    public static Map<String, Boolean> getCollisionDirection(Entity object1, IGameObject object2, ECollisionAxis axis) {
        Map<String, Boolean> collisionTypes = new HashMap<>(Map.of(
                "top", false,
                "bottom", false,
                "right", false,
                "left", false
        ));

        if (testCollision(object1, object2)) {
            if (axis == ECollisionAxis.X_AXIS) {
                if (object1.getVelX() > 0) {
                    collisionTypes.replace("right", true);
                }
                if (object1.getVelX() < 0) {
                    collisionTypes.replace("left", true);
                }
            }

            if (axis == ECollisionAxis.Y_AXIS) {
                if (object1.getVelY() < 0) {
                    collisionTypes.replace("top", true);
                }
                if (object1.getVelY() > 0) {
                    collisionTypes.replace("bottom", true);
                }
            }
        }

        return collisionTypes;
    }

}
