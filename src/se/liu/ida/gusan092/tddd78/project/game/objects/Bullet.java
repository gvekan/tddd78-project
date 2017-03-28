package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GameObject
{
    private ControlledObject controlledObject;

    public Bullet(final int x, final int y,
		     final Handler handler, ControlledObject controlledObject)
    {
	super(x, y, 3, 3, Type.BULLET, handler);
	this.controlledObject = controlledObject;
	setVelY(-1);
    }

    @Override public void collision(final GameObject collision, final Side side) {

    }

    @Override public void tick() {
	y += velY;
	ArrayList<GameObject> collisions = handler.getCollisions(this);
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
			collision.collision(collision, Side.FRONT);
		}
		handler.remove(this);
	    }
	}
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(Color.RED);
	g2d.fill(getBounds());
    }
}
