package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.StillObject;

import java.awt.Color;
import java.util.List;

public abstract class PowerUp extends StillObject
{
    protected boolean activated = false;
    protected ControlledObject controlledObject = null;
    protected PowerUp interruptedPowerUp = null;
    protected PowerUpId id;

    protected PowerUp(final int x, final Color color, final Handler handler, final PowerUpId id)
    {
	super(x, -20, 20, 20, color, Type.POWERUP, handler);
	this.id = id;
    }

    public PowerUpId getId() {
	return id;
    }

    public void activate(final ControlledObject controlledObject) {
        activated = true;
        controlledObject.setColor(color);
   	controlledObject.addPowerUp(this);
    }

    protected void reset() {
	controlledObject.removePowerUp(this);
	if (interruptedPowerUp != null) {
	    controlledObject.addPowerUp(interruptedPowerUp);
	}
	List<PowerUp> powerUps = controlledObject.getPowerUps();
	if (powerUps.isEmpty()) {
	    controlledObject.resetColor();
	} else {
	    PowerUp powerUp = powerUps.get(powerUps.size()-1);
	    controlledObject.setColor(powerUp.getColor());
	}
    }

    @Override public void collisionWithGameObject(final GameObject collision, final Side side) {
	handler.remove(this);
    }

    @Override public void collisionWithControlled(final ControlledObject collision, final Side side) {
	handler.remove(this);
	this.controlledObject = collision;
    }
    public void collisionHasSamePowerUp() {
        controlledObject.setColor(color);
    }

    public abstract void use();
    public abstract void stop();
    public abstract String decription();
}
