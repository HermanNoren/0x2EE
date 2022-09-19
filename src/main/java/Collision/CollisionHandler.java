package Collision;

import config.Config;
import controllers.EDirection;
import helperclasses.Vector2;
import mapclasses.Tile;
import sprites.Player;
import sprites.enemies.Enemy;

public class CollisionHandler {
    private Player player;
    private Enemy enemy;
    private Tile tile;

    /**
     * Method for calculating if the player is overlapping/close to a wall.
     * If so the method will return true, which makes the player unable to
     * walk further in said direction. Now hardcoded to the player being square.
     * First Check is to see if the player is to the right of the tile, second to see if
     * it is to the left
     * @param player
     * @param tile
     */

    public void playerCollidesWithTile(Player player, Tile tile){
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
