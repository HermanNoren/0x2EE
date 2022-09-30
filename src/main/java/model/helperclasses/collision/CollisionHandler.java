package model.helperclasses.collision;

import config.Config;
import controllers.EDirection;
import model.gameobjects.Entity;
import model.gameobjects.IGameObject;
import model.helperclasses.Vector2;
import model.gameobjects.Player;
import model.gameobjects.enemies.Enemy;
import model.mapclasses.Terrain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
     */
    public static boolean playerCollidesWithTerrain(IGameObject player, IGameObject terrain){
        if (!terrain.isPassable()){
            return
                    player.getPos().x < terrain.getPos().x*48 + terrain.getWidth()
                            && player.getPos().x + player.getWidth() > terrain.getPos().x*48
                            && player.getPos().y < terrain.getPos().y*48 + terrain.getHeight()
                            && player.getHeight() + player.getPos().y > terrain.getPos().y*48;
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
                object1.getPos().x < object2.getPos().x + object2.getWidth()
                && object1.getPos().x + object1.getWidth() > object2.getPos().x
                && object1.getPos().y < object2.getPos().y + object2.getHeight()
                && object1.getHeight() + object1.getPos().y > object2.getPos().y;
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
                if(object1.getVelX() > 0){
                    if (object1.getDirection() == EDirection.RIGHT) {
                        collisionTypes.replace("right", true);
                    }

                    if (object1.getDirection() == EDirection.LEFT) {
                        collisionTypes.replace("left", true);
                    }

                }
            }

            if (Objects.equals(direction, "Y")) {
                if (object1.getVelY() > 0){
                    if (object1.getDirection() == EDirection.UP) {
                        collisionTypes.replace("top", true);
                    }
                    if (object1.getDirection() == EDirection.DOWN) {
                        collisionTypes.replace("bottom", true);
                    }

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
