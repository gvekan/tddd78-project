package se.liu.ida.gusan092.tddd78.project.game.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.collision.CollisionHandler;
import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A powerUp changing the players collisionhandler
 */
public class Unstoppable extends PowerUp implements CollisionHandler
{
    private CollisionHandler oldCollisionHandeler = null;
    private int countdown = 5;

    public Unstoppable(final Player player)
    {
	super(PowerUpId.UNSTOPPABLE, player);
    }

    public Unstoppable(final Player player, final String saveValues)
    {
	super(PowerUpId.UNSTOPPABLE, player);
	countdown = Integer.parseInt(saveValues);
    }

    @Override public void use() {
    }

    @Override public void stop() {
    }

    @Override public void interrupt() {
        if (timer != null) timer.stop();
        if (oldCollisionHandeler != null) player.setCollisionHandler(oldCollisionHandeler);
    }

    @Override public void resume() {
	oldCollisionHandeler = player.getCollisionHandler();
	player.setCollisionHandler(this);
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	        if (running) {
		    countdown--;
		    if (countdown == 0) {
			player.setCollisionHandler(oldCollisionHandeler);
			reset();
		    }
		}
	    }
	};
	timer = new Timer(1000, taskPerformer);
	timer.start();
    }

    @Override public String getSaveValues() {
	return super.getSaveValues() + SavedProperties.ENUM_SPLIT + Integer.toString(countdown);
    }

    @Override public void collisionHasSamePowerUp() {
	countdown += 5;
    }

    @Override public String description() {
	return "Unstoppable: " + countdown;
    }

    @Override public void collision(final Game game, final Handler handler, final Player player,
				    final GameObject collision, final Side side)
    {
        switch (collision.getType()) {
	    case ANIMAL:
	    case ROADBLOCK:
	        handler.removeAfterTick(collision);
	        player.addScore(100);
	        break;
	    case CONTAINER:
	        collision.collisionWithPlayer(player, side);
	}
    }

}
