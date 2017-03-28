package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.CollisionHandlerControlled;
import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.*;

public class Unstoppable extends PowerUp implements CollisionHandlerControlled
{
    private CollisionHandlerControlled oldCollisionHandeler = null;
    private long timer = 0;
    private int countdown = 5;

    public Unstoppable(final int x, final Handler handler)
    {
	super(x, handler, Color.YELLOW);
    }

    @Override public void activate(final ControlledObject controlledObject) {
        super.activate(controlledObject);
	oldCollisionHandeler = controlledObject.getCollisionHandler();
	controlledObject.setCollisionHandler(this);
    }

    @Override public void use() {

    }

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {
        switch (collision.getType()) {
	    case TRAFFIC_BARRIER:
	    case ROADBLOCK:
	        handler.remove(collision);
	        controlledObject.addScore(100);
	}
    }

    @Override public void collisionActivated(final Game game, final Handler handler, final ControlledObject controlledObject,
					     final GameObject collision, final Side side)
    {

    }

    @Override public void maxTick() {
        if (countdown == 0) {
	    controlledObject.setCollisionHandler(oldCollisionHandeler);
	    controlledObject.setColor(oldColor);
	    controlledObject.removePowerUp(this);
	}
	if (timer > 0) {
	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		countdown--;
	    }
	} else if (activated){
	    timer = System.currentTimeMillis();
	}
    }
}
