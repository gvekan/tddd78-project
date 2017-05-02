package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class for a PowerUp
 */
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

    public PowerUpId getId() {
	return id;
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

    public void addInterrupted(final PowerUp powerUp) {
	interrupted.add(powerUp);
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
