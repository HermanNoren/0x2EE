package model.helperclasses.collision;

import config.Config;
import controllers.EDirection;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.gameobjects.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CollisionHandler {
    /**
     * Method for calculating if the player is overlapping/close to a wall.
     * If so the method will return true, which makes the player unable to
     * walk further in said direction. Now hardcoded to the player being square.
     * First Check is to see if the player is to the right of the tile, second to see if
     * it is to the left
     * @param player
     */
    public static boolean playerCollidesWithTerrain(IGameObject player, IGameObject terrain){
        if (!terrain.isPassable()){
            return
                    player.getPos().getX() < terrain.getPos().getX() + terrain.getWidth()
                            && player.getPos().getX() + player.getWidth() > terrain.getPos().getX()
                            && player.getPos().getY() < terrain.getPos().getY() + terrain.getHeight()
                            && player.getHeight() + player.getPos().getY() > terrain.getPos().getY();
        }
        return false;
    }

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

    public static Map<String, Boolean> testCollisionWithDirection(Entity object1, IGameObject object2, String direction) {
        Map<String, Boolean> collisionTypes = new HashMap<>(Map.of(
                "top", false,
                "bottom", false,
                "right", false,
                "left", false
        ));

        if (playerCollidesWithTerrain(object1, object2)) {
            if (Objects.equals(direction, "X")) {
                if (object1.getVelX() > 0) {
                    collisionTypes.replace("right", true);
                }

                if (object1.getVelX() < 0) {
                    collisionTypes.replace("left", true);
                }

            }

            if (Objects.equals(direction, "Y")) {
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
