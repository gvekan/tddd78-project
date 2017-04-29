package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class PowerUp
{
    protected Player player = null;
    protected List<PowerUp> interrupted = new ArrayList<>();
    protected boolean added = false;
    protected boolean running = true;

    protected PowerUpId id;

    protected PowerUp(final PowerUpId id, final Player player)
    {
	this.id = id;
	this.player = player;
    }

    protected void reset() {
	player.removePowerUp(this);
	if (!interrupted.isEmpty()) {
	    for (PowerUp powerUp: interrupted) {
	    player.addPowerUp(powerUp);
	    powerUp.resume();
	    }
	}
    }

    protected void add() {
	List<PowerUp> powerUps = player.getPowerUps();
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.id == id) {
		powerUp.collisionHasSamePowerUp();
		return;
	    }
	}
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (id.isIncompatible(powerUp.id)) {
	        powerUps.remove(powerUp);
		powerUp.interrupt();
		interrupted.add(powerUp);
		break;
	    }
	}
   	player.addPowerUp(this);
   	added = true;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<PowerUp> getInterrupted() {
        return interrupted;
    }

    public String getSaveValues() {
        return Integer.toString(id.getIndex());
    }

    public abstract void collisionHasSamePowerUp();
    public abstract void use();
    public abstract void stop();
    public abstract void interrupt();
    public abstract void resume();
    public abstract String description();
}
