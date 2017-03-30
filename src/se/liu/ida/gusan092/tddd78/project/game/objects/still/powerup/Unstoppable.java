package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.CollisionHandlerControlled;
import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Unstoppable extends PowerUp implements CollisionHandlerControlled
{
    private CollisionHandlerControlled oldCollisionHandeler = null;
    private int countdown = 5;
    private Timer timer = null;

    public Unstoppable(final int x, final Handler handler)
    {
	super(x, Color.YELLOW, handler, PowerUpId.UNSTOPPABLE);
    }

    @Override public void activate(final ControlledObject controlledObject) {
        super.activate(controlledObject);
	oldCollisionHandeler = controlledObject.getCollisionHandler();
	controlledObject.setCollisionHandler(this);
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		countdown--;
		if (countdown == 0) {
		    controlledObject.setCollisionHandler(oldCollisionHandeler);
		    reset();
		    timer.stop();
		}
	    }
	};
	timer = new Timer(1000, taskPerformer);
	timer.start();
    }

    @Override public void use() {

    }

    @Override public void stop() {

    }

    @Override public void collisionHasSamePowerUp() {
        super.collisionHasSamePowerUp();
	countdown += 5;
    }

    @Override public String decription() {
	return "Unstoppable: " + countdown;
    }

    @Override public void collisionWithControlled(final ControlledObject collision, final Side side) {
	super.collisionWithControlled(collision, side);
	List<PowerUp> powerUps = collision.getPowerUps();
	for (int i = 0; i < powerUps.size(); i++) {
	    PowerUp powerUp = powerUps.get(i);
	    if (powerUp.getId() == id) {
		powerUp.collisionHasSamePowerUp();
		return;
	    }
	}
	activate(collision);
    }

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {
        switch (collision.getType()) {
	    case TRAFFIC_BARRIER:
	    case ROADBLOCK:
	        handler.remove(collision);
	        controlledObject.addScore(100);
	        break;
	    case POWERUP:
	        collision.collisionWithControlled(controlledObject, side);
	}
    }

    @Override public void collisionWithControlled(final Game game, final Handler handler, final ControlledObject controlledObject,
						  final ControlledObject collision, final Side side)
    {

    }
}
