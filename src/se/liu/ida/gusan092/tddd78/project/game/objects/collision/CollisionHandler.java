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
     * Used to handle a collision with a GameObject
     */
    public void collision(final Game game, final Handler handler, final Player player, final GameObject collision, final Side side);

    /**
     * Not done, will be used in a extension for multiplaying
     * Used in multiplayer to handle collision between players
     */
    public void collisionWithPlayer(final Game game, final Handler handler, final Player player, final Player collision, final Side side);
}
