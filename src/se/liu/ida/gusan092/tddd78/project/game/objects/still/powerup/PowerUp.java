package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Color;
import java.util.List;

public abstract class PowerUp
{
    protected ControlledObject controlledObject = null;
    protected PowerUp interruptedPowerUp = null;
    protected boolean activated = false;

    protected Handler handler;
    protected PowerUpId id;
    protected Color color;

    protected PowerUp(final Handler handler, final PowerUpId id, final Color color)
    {
        this.handler = handler;
	this.id = id;
	this.color = color;
    }

    public PowerUpId getId() {
	return id;
    }

    public Color getColor() {
	return color;
    }

    public void activate(final ControlledObject controlledObject) {
        this.controlledObject = controlledObject;
	List<PowerUp> powerUps = controlledObject.getPowerUps();
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.id == id) {
		controlledObject.setColor(color);
		powerUp.collisionHasSamePowerUp();
		return;
	    }
	}
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (id.isIncompatible(powerUp.id)) {
	        powerUps.remove(powerUp);
		powerUp.interrupt();
		interruptedPowerUp = powerUp;
		break;
	    }
	}
        controlledObject.setColor(color);
   	controlledObject.addPowerUp(this);
   	activated = true;
    }

    protected void reset() {
	controlledObject.removePowerUp(this);
	if (interruptedPowerUp != null) {
	    controlledObject.addPowerUp(interruptedPowerUp);
	    interruptedPowerUp.resume();
	}
	List<PowerUp> powerUps = controlledObject.getPowerUps();
	if (powerUps.isEmpty()) {
	    controlledObject.resetColor();
	} else {
	    PowerUp powerUp = powerUps.get(powerUps.size()-1);
	    controlledObject.setColor(powerUp.color);
	}
    }

    public abstract void collisionHasSamePowerUp();
    public abstract void use();
    public abstract void stop();
    public abstract void interrupt();
    public abstract void resume();
    public abstract String description();
}
