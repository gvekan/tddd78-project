package se.liu.ida.gusan092.tddd78.project.game.objects.still;


import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Animal extends StillObject
{

    public Animal(final int x, final Handler handler)
    {
	super(x, -15, 15, 15, Color.GREEN, Type.ANIMAL, handler);
	if ( x < Game.WIDTH/2) setVelX(4);
	else setVelX(-4);

	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	        if (running) {
		    setX(getX() + velX);
		    if (velX < 0) handleCollisionLeft();
		    else handleCollisionRight();
		}
	    }
	};
	Timer timer = new Timer(100, taskPerformer);
	timer.start();
    }

    public void handleCollisionLeft() {
	List<GameObject> collisions = handler.getCollisions(getLeftBound(),this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		switch (collision.getType()) {
		    case TRAFFIC_BARRIER:
		    case ROADBLOCK:
			velX = -velX;
			x += velX;
			break;
		    case PLAYER:
			collision.collisionWithGameObject(collision, Side.LEFT);
			break;
		    case POWERUP:
			handler.removeAfterTick(collision);
		}
	    }
	}
    }
    public Rectangle getLeftBound() {return new Rectangle(x, y,1,height);}

    public void handleCollisionRight() {
	List<GameObject> collisions = handler.getCollisions(getRightBound(),this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		switch (collision.getType()) {
		    case TRAFFIC_BARRIER:
		    case ROADBLOCK:
		        velX = -velX;
		        x += velX;
		        break;
		    case PLAYER:
			collision.collisionWithGameObject(collision, Side.RIGHT);
			break;
		    case POWERUP:
			handler.removeAfterTick(collision);
		}
	    }
	}
    }
    public Rectangle getRightBound() {return new Rectangle(x+width-1, y,1,height);}

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(color);
	g2d.fillOval(x,y,width,height);
    }
}
