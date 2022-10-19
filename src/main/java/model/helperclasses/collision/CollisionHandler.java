package model.helperclasses.collision;

import config.Config;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.Vector2;
import model.mapclasses.Tile;

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
                        && object1.getHeight() + object1.getPos().getY() > object2.getPos().getY();
    }

    /**
     * Method for providing which specific tile pieces an object is colliding with
     * @param object Object to test
     * @param tile Matrix containing all the tile tiles.
     * @return A list containing the tile pieces that is being collided with
     */
    public static List<Tile> getSpecificTileCollisions(IGameObject object, Tile[][] tile) {
        List<Tile> collidedTile = new ArrayList<>();

        // Defining the bounds of how many tiles to iterate through.
        // No need to look further away than just around the object!
        Vector2 objectPos = object.getPos();
        int left = (int) (objectPos.getX() / Config.TILE_SIZE - 1);
        int right = (int) (objectPos.getX() / Config.TILE_SIZE + 2);
        int up = (int) (objectPos.getY() / Config.TILE_SIZE - 1);
        int down = (int) (objectPos.getY() / Config.TILE_SIZE + 2);

        if (left < 0) {
            left = 0; // Unreachable
        }
        if (up < 0) {
            up = 0; // Unreachable
        }

        for (int row = left; row < right && row < tile.length; row++) {
            for (int col = up; col < down && col < tile[row].length; col++) {
                Tile t = tile[row][col];
                if (!t.isPassable()) {
                    if (testCollision(object, t)) {
                        collidedTile.add(t);
                    }
                }
            }
        }
        return collidedTile;
    }

    /**
     * Method for calculating in which direction a collision is happening.
     * @param movingObject Object to which is colliding with a direction
     * @param otherObject Object which the first object is colliding into.
     * @param axis The desired axis to test. If desired axis is X_AXIS the method will test if collision is happening
     *             to the right or to the left. If desired axis is Y_AXIS the method will test if collision is happening
     *             at the top or at the bottom.
     * @return The method returns a map containing 4 keys; 'top', 'bottom', 'right', 'left'. Use these keys to get the
     * boolean value of the direction. Example: map.get("top") will be true if collision is at the top.
     */
    public static Map<String, Boolean> getCollisionDirection(Entity movingObject, IGameObject otherObject, ECollisionAxis axis) {
        Map<String, Boolean> collisionDirection = new HashMap<>(Map.of(
                "top", false,
                "bottom", false,
                "right", false,
                "left", false
        ));

        if (testCollision(movingObject, otherObject)) {
            if (axis == ECollisionAxis.X_AXIS) {
                if (movingObject.getVelX() > 0) {
                    collisionDirection.replace("right", true);
                }
                if (movingObject.getVelX() < 0) {
                    collisionDirection.replace("left", true);
                }
            }

            if (axis == ECollisionAxis.Y_AXIS) {
                if (movingObject.getVelY() < 0) {
                    collisionDirection.replace("top", true);
                }
                if (movingObject.getVelY() > 0) {
                    collisionDirection.replace("bottom", true);
                }
            }
        }
        return collisionDirection;
    }
}
