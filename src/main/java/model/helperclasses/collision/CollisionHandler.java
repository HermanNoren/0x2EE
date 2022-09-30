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
    private Player player;
    private Enemy enemy;

    /**
     * Method for calculating if the player is overlapping/close to a wall.
     * If so the method will return true, which makes the player unable to
     * walk further in said direction. Now hardcoded to the player being square.
     * First Check is to see if the player is to the right of the tile, second to see if
     * it is to the left
     * @param player
     * @param tile
     */
    public void playerCollidesWithTile(Player player, Terrain tile){
        if(
                player.getPos().x >(tile.getPos().x + tile.getHeight() + tile.getWidth())
                || (player.getPos().x + player.getWidth()+ player.getHealth() < tile.getPos().x)
                ||(player.getPos().y <tile.getPos().y + tile.getHeight())
                ||(player.getPos().y + player.getHealth() > tile.getPos().y)
        ){
            System.out.println("yes");
        }
    }

    /**
     * Method for providing which specific terrain pieces an object is colliding with
     * @param object Object to test
     * @param terrain List of terrain pieces
     * @return A list containing the terrain pieces that is being collided with
     */
    public static List<Terrain> getSpecificTerrainCollisions(IGameObject object, Terrain[][] terrain) {
        List<Terrain> collidedTerrain = new ArrayList<>();

        Vector2 objectPos = object.getPos();
        int left = (int) (objectPos.x / object.getWidth() - 1);
        int right = (int) (objectPos.x / object.getWidth() + 1);
        int up = (int) (objectPos.y / object.getHeight() - 1);
        int down = (int) (objectPos.y / object.getHeight() + 1);

        if (left < 0) { left = 0; }
        if (up < 0) { up = 0; }

        for (int row = left; row < right && row < terrain[0].length; row++) {
            for (int col = up; col < down && col < terrain.length; col++) {
                Terrain t = terrain[row][col];
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
     * Method for calculating if a game object is colliding with another game object
     * @param object1 first object to compare
     * @param object2 second object to compare
     * @return true if objects are colliding, else false
     */
    public static boolean testCollision(IGameObject object1, IGameObject object2) {
        return
                object1.getPos().x < object2.getPos().x + object2.getWidth()
                && object1.getPos().x + object1.getWidth() > object2.getPos().x
                && object1.getPos().y < object2.getPos().y + object2.getHeight()
                && object1.getHeight() + object1.getPos().y > object2.getPos().y;
    }

    public static Map<String, Boolean> getCollisionDirection(Entity object1, IGameObject object2, ECollisionDirection direction) {
        Map<String, Boolean> collisionTypes = new HashMap<>(Map.of(
                "top", false,
                "bottom", false,
                "right", false,
                "left", false
        ));

        if (testCollision(object1, object2)) {
            if (direction == ECollisionDirection.X_AXIS) {
                if (object1.getVelX() > 0) {
                    collisionTypes.replace("right", true);
                }
                if (object1.getVelX() < 0) {
                    collisionTypes.replace("left", true);
                }
            }

            if (direction == ECollisionDirection.Y_AXIS) {
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


    /**
     * If the player every try to go outside the border of the map this method will be called, stopping the
     * velocity of the player in the direction it tries to leave in (might need extra attention in the corners).
     * @param player
     */
    public static void seeIfPlayerIsOutsideBorder(Player player){
        Vector2 playerPosition = player.getPos();
        if(playerPosition.y <= 0)
            player.setDirection(EDirection.DOWN);
        if (playerPosition.y >= Config.SCREEN_HEIGHT_IN_GAME - player.getHeight())
            player.setDirection(EDirection.UP);
        if (playerPosition.x < 0)
            player.setDirection(EDirection.RIGHT);
        if(playerPosition.x >= Config.SCREEN_WIDTH_IN_GAME - player.getWidth())
            player.setDirection(EDirection.LEFT);
    }
}
