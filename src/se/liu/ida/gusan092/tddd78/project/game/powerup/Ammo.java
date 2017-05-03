package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A powerup that creates bullets with use
 */
public class Ammo extends PowerUp
{
    /**
     * The delay of bullets
     */
    public static final int DELAY = 100;
    private Handler handler;
    private int ammo = 10;

    public Ammo(final Player player, final Handler handler) {
	super(PowerUpId.AMMO, player);
	this.handler = handler;
    }
    public Ammo(final Player player, final Handler handler, final String saveValues) {
    	super(PowerUpId.AMMO, player);
    	this.handler = handler;
	ammo = Integer.parseInt(saveValues);
    }


    private void createTimer() {
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		handler.add(new Bullet(player.getX() + player.getWidth() / 2, player.getY() - 4, handler, player));
		ammo--;
		if (ammo == 0) {
		    reset();
		}
	    }
	};
	timer = new Timer(DELAY, taskPerformer);
	timer.start();
    }


    @Override public void use() {
        createTimer();
    }

    @Override public void stop() {
        interrupt();
    }

    @Override public void interrupt() {
	if (timer != null)
	timer.stop();
 	timer = null;
    }

    @Override protected void reset() {
	super.reset();
	interrupt();
    }

    @Override public void resume() {
    }

    @Override public void collisionHasSamePowerUp() {
	ammo += 10;
    }

    @Override public String description() {
	return "Ammo: " + ammo;
    }

    @Override public String getSaveValues() {
	return super.getSaveValues() + SavedProperties.ENUM_SPLIT + Integer.toString(ammo);
    }
}