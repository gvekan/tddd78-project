package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.util.List;

public abstract class PowerUp
{
    protected ControlledObject controlledObject = null;
    protected PowerUp interruptedPowerUp = null;
    protected boolean activated = false;

    protected PowerUpId id;

    protected PowerUp(final PowerUpId id, final ControlledObject controlledObject)
    {
	this.id = id;
	this.controlledObject = controlledObject;

	List<PowerUp> powerUps = controlledObject.getPowerUps();
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.id == id) {
		controlledObject.setColor(id.getColor());
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
        controlledObject.setColor(id.getColor());
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
	    controlledObject.setColor(powerUp.id.getColor());
	}
    }

    public abstract void collisionHasSamePowerUp();
    public abstract void use();
    public abstract void stop();
    public abstract void interrupt();
    public abstract void resume();
    public abstract String description();
}
