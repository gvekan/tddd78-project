package se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup;

import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.CollisionHandlerControlled;
import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Unstoppable extends PowerUp implements CollisionHandlerControlled
{
    private CollisionHandlerControlled oldCollisionHandeler = null;
    private int countdown = 5;
    private Timer timer = null;

    public Unstoppable(final int x, final Handler handler)
    {
	super(x, Color.YELLOW, handler);
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

    @Override public void collision(final Game game, final Handler handler, final ControlledObject controlledObject,
				    final GameObject collision, final Side side)
    {
        switch (collision.getType()) {
	    case TRAFFIC_BARRIER:
	    case ROADBLOCK:
	        handler.remove(collision);
	        controlledObject.addScore(100);
	}
    }

    @Override public void collisionWithControlled(final Game game, final Handler handler, final ControlledObject controlledObject,
						  final ControlledObject collision, final Side side)
    {

    }
}
