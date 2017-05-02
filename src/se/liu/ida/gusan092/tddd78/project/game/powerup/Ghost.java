package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.collision.CollisionHandler;

/**
 * A powerup that changes the players collisionhandler in use
 */
public class Ghost extends PowerUp implements CollisionHandler
{
    public Ghost(final Player player) {
    	super(PowerUpId.GHOST, player);
    }

    public Ghost(final Player player, final String saveValues) {
	super(PowerUpId.GHOST, player);
    }

    @Override public void use() {

    }

    @Override public void stop() {

    }

    @Override public void interrupt() {

    }

    @Override public void resume() {

    }

    @Override public void collisionHasSamePowerUp() {

    }

    @Override public String description() {
        return null;
    }

    @Override public void collision(final Game game, final Handler handler, final Player player,
				    final GameObject collision, final Side side)
    {

    }

    @Override
    public void collisionWithPlayer(final Game game, final Handler handler, final Player player, final Player collision,
                                    final Side side)
    {

    }
}
