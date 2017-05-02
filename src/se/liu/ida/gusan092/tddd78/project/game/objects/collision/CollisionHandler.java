package se.liu.ida.gusan092.tddd78.project.game.objects.collision;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

/**
 * A interface for handeling collisions for player
 */
public interface CollisionHandler
{
    /**
     * @param game the game the player is in
     * @param handler the handler the player is in
     * @param player the player
     * @param collision the gameobject it has collided with
     * @param side the side the collision happend
     */
    public void collision(final Game game, final Handler handler, final Player player, final GameObject collision, final Side side);

    /**
     * Used in multiplayer to handle collision between players
     */
    public void collisionWithPlayer(final Game game, final Handler handler, final Player player, final Player collision, final Side side);
}
