package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.collision.CollisionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A powerup that changes the players collisionhandler in use
 */
public class Ghost extends PowerUp implements CollisionHandler
{
    private CollisionHandler oldCollisionHandeler = null;

    private int countSec = 5;
    private int countTenOfaSec = 0;
    /**
     * Ten of a second in milliseconds
     */
    public static final int DELAY = 100;

    public Ghost(final Player player) {
    	super(PowerUpId.GHOST, player);
    }

    public Ghost(final Player player, final String saveValues) {
	super(PowerUpId.GHOST, player);
    }

    private void createTimer() {
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		if (countSec == 0 && countTenOfaSec == 0) {
		    reset();
		}else if (countTenOfaSec == 0) {
	            countTenOfaSec = 9;
	            countSec--;
		} else countTenOfaSec--;
	    }
	};
	timer = new Timer(DELAY, taskPerformer);
	timer.start();
    }


    @Override public void use() {
	oldCollisionHandeler = player.getCollisionHandler();
	player.setCollisionHandler(this);
	createTimer();
    }

    @Override public void stop() {
	interrupt();
    }

    @Override public void interrupt() {
	if (timer != null) timer.stop();
	if (oldCollisionHandeler != null) player.setCollisionHandler(oldCollisionHandeler);
	timer = null;
    }

    @Override protected void reset() {
	super.reset();
	interrupt();
    }

    @Override public void resume() {

    }

    @Override public void collisionHasSamePowerUp() {
        countSec += 5;
    }

    @Override public String description() {
        return "Ghost: " + countSec + ".0" + countTenOfaSec;
    }

    @Override public void collision(final Game game, final Handler handler, final Player player,
				    final GameObject collision, final Side side)
    {

    }

    @Override
    public void collisionWithPlayer(final Game game, final Handler handler, final Player player, final Player collision,
                                    final Side side)
    {

    }
}
