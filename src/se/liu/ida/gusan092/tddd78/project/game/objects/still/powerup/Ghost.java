package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.CollisionHandlerControlled;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Color;

public class Ghost extends PowerUp implements CollisionHandlerControlled
{

    public Ghost(final ControlledObject controlledObject) {
	super(PowerUpId.GHOST, controlledObject);
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
