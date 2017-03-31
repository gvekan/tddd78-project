package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.StillObject;


public class Container extends StillObject
{
    private PowerUpId id;
    private boolean collided = false;

    public Container(final int x, final Handler handler, final PowerUpId id)
    {
	super(x,-20, 20, 20, id.getColor(), Type.POWERUP, handler);
    	this.id = id;
    }

    @Override public void tick() {
	super.tick();
	if (collided) {
	    handler.remove(this);
	}
    }

    @Override public void collisionWithGameObject(final GameObject collision, final Side side) {
	collided = true;
    }

    @Override public void collisionWithControlled(final ControlledObject collision, final Side side) {
        collided = true;
	switch (id) {
	    case AMMO:
	        new Ammo(collision, handler);
	        break;
	    case GHOST:
	        new Ghost(collision);
	        break;
	    case UNSTOPPABLE:
	        new Unstoppable(collision);
	        break;
	}
    }
}
