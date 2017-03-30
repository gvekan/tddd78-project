package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.StillObject;


public class Container extends StillObject
{
    private PowerUp powerUp;

    public Container(final int x, final Handler handler, final PowerUp powerUp)
    {
	super(x,-20, 20, 20, powerUp.getColor(), Type.POWERUP, handler);
    	this.powerUp = powerUp;
    }

    @Override public void collisionWithControlled(final ControlledObject collision, final Side side) {
	handler.remove(this);
	powerUp.activate(collision);
    }
}
