package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract class for a PowerUp
 */
public abstract class PowerUp
{
    protected Player player = null;
    protected List<PowerUp> interrupted = new ArrayList<>();
    protected boolean running = true;
    protected Timer timer;

    protected PowerUpId id;

    protected PowerUp(final PowerUpId id, final Player player)
    {
	this.id = id;
	this.player = player;
	timer = null;
    }

    public PowerUpId getId() {
	return id;
    }

    /**
     * If overrided this should be runned in the end
     */
    protected void reset() {
        interrupt();
	player.removePowerUp(this);
	if (!interrupted.isEmpty()) {
	    for (PowerUp powerUp: interrupted) {
	        player.addInterruptedPowerUp(powerUp);
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

    /**
     * Should be called when added to the players power ups
     */
    public abstract void resume();
    public abstract String description();
}
