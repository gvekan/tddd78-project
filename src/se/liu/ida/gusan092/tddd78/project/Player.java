package se.liu.ida.gusan092.tddd78.project;

import java.awt.Graphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;


public class Player extends GameObject
{
    private int width = 20;
    private int height = 45;
    private int downTimer;


    private Handeler handeler;
    private CollisionHandeler collisionHandeler = new DefaultCollision();

    public Player(final int x, final int y, final Id id, Handeler handeler) {
        super(x, y, id);
        this.handeler = handeler;
    }

    @Override public void tick() {
        int oldX = x;
        int oldY = y;
	x = Game.clamp(x + velX,0,Game.WIDTH-width);
	List<GameObject> collisionsX = handeler.getCollisions(this);
	if (!collisionsX.isEmpty()) {
	    x = oldX;
	    for (GameObject collision:
		    collisionsX) {
		collisionHandeler.collision(collision);
	    }
	}
	y = Game.clamp(y + velY,0,Game.HEIGHT-height);
	List<GameObject> collisionsY = handeler.getCollisions(this);
	if (!collisionsY.isEmpty()) {
	    y = oldY;
	    for (GameObject collision:
		    collisionsY) {
	        if (!collisionsX.contains(collision)) {
		    collisionHandeler.collision(collision);
		}
	    }
	}

    }

    @Override public void render(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.CYAN);
        g2d.fill(getBounds());
    }

    @Override public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
