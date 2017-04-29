package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;

import java.util.List;
import java.awt.Color;

public class Bullet extends GameObject
{
    private Player controlledObject;

    public Bullet(final int x, final int y, final Handler handler, final Player controlledObject)
    {
	super(x, y, 3, 3, Color.BLACK, Type.BULLET, handler);
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
			handler.removeAfterTick(collision);
			controlledObject.addScore(100);
			break;
		    case ANIMAL:
			handler.removeAfterTick(collision);
			controlledObject.addScore(200);
			break;
		    case PLAYER:
			collision.collisionWithGameObject(collision, Side.FRONT);
			break;
		    case POWERUP:
		        collision.collisionWithPlayer(controlledObject, Side.FRONT);
		}
		handler.removeAfterTick(this);
	    }
	}
	if (y == 0) {
	    handler.removeAfterTick(this);
	}
    }
}
