package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.StillObject;

import java.awt.*;

public abstract class PowerUp extends StillObject
{
    protected boolean activated = false;
    protected ControlledObject controlledObject = null;
    protected Color color;
    protected Color oldColor = null;

    protected PowerUp(final int x, final Handler handler, Color color)
    {
	super(x, -20, 20, 20, Type.POWERUP, handler);
	this.color = color;
    }

    @Override public void collision(final GameObject collision, final Side side) {
        collision.powerUpCollision(this);
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(color);
	g2d.fill(getBounds());
    }

    public void activate(ControlledObject controlledObject) {
        activated = true;
        this.controlledObject = controlledObject;
        oldColor = controlledObject.getColor();
        controlledObject.setColor(color);
        handler.remove(this);
   	controlledObject.addPowerUp(this);
    }

    protected void reset() {
	controlledObject.setColor(oldColor);
	controlledObject.removePowerUp(this);
    }

    public abstract void use();
}
