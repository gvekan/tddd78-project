package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

public class Bullet extends GameObject
{
    private ControlledObject controlledObject;

    public Bullet(final int x, final int y, final Handler handler, ControlledObject controlledObject)
    {
	super(x, y, 3, 3, controlledObject.getColor(), Type.BULLET, handler);
	this.controlledObject = controlledObject;
	setVelY(-1);
    }

    @Override public void tick() {
	y += velY;
	List<GameObject> collisions = handler.getCollisions(this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		switch (collision.getType()) {
		    case TRAFFIC_BARRIER:
		    case ROADBLOCK:
			handler.remove(collision);
			controlledObject.addScore(100);
			break;
		    case PLAYER:
			collision.collisionAsGameObject(collision, Side.FRONT);
		}
		handler.remove(this);
	    }
	}
	if (y == 0) {
	    handler.remove(this);
	}
    }
}
