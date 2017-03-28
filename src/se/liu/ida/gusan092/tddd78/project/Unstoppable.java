package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;

public class Unstoppable extends PowerUp implements CollisionHandlerControlled
{
    private ControlledObject controlledObject = null;
    private CollisionHandlerControlled oldCollisionHandeler = null;
    private Color oldColor = null;
    private long timer = 0;
    private int countdown = 5;

    protected Unstoppable(final int x, final Handler handler)
    {
	super(x, handler);
    }

    @Override public void activate(final ControlledObject controlledObject) {
	if (!activated) {
	    activated = true;
	    this.controlledObject = controlledObject;
	    oldCollisionHandeler = controlledObject.getCollisionHandler();
	    controlledObject.setCollisionHandler(this);
	    oldColor = controlledObject.getColor();
	    controlledObject.setColor(Color.YELLOW);
	    handler.remove(this);
	    controlledObject.addPowerUp(this);
	}
    }

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {
        switch (collision.getObjectType()) {
	    case TRAFFIC_BARRIER:
	    case ROADBLOCK:
	        handler.remove(collision);
	        controlledObject.addScore(100);
	}

    }

    @Override public void collisionControlled(final Game game, final Handler handler, final ControlledObject controlledObject,
					      final GameObject collision, final Side side)
    {

    }

    @Override public void collision(final GameObject collision, final Side side) {
        collision.powerUpCollision(this);
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

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(Color.YELLOW);
	g2d.fill(getBounds());
    }
}
