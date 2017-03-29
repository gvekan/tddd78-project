package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.CollisionHandlerControlled;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.*;

public class Ghost extends PowerUp implements CollisionHandlerControlled
{

    protected Ghost(final int x, final Color color, final Handler handler) {
	super(x, Color.PINK, handler);
    }

    @Override public void use() {

    }

    @Override public void stop() {

    }

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {

    }

    @Override
    public void collisionWithControlled(final Game game, final Handler handler, final ControlledObject controlledObject,
					final ControlledObject collision, final Side side)
    {

    }
}
