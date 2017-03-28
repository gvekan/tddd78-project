package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;

public class Bullet extends GameObject
{
    private ControlledObject controlledObject;

    protected Bullet(final int x, final int y,
		     final Handler handler, ControlledObject controlledObject)
    {
	super(x, y, 1, 1, ObjectType.BULLET, handler);
	this.controlledObject = controlledObject;
	setVelY(-1);
    }

    @Override public void collision(final GameObject collision, final Side side) {

    }

    @Override public void tick() {
	y += y;
    }

    @Override public void maxTick() {

    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(Color.RED);
	g2d.fill(getBounds());
    }
}
