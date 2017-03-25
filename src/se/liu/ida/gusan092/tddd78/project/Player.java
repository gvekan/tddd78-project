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

    private boolean halfTick = true;

    private CollisionHandeler collisionHandeler = new DefaultCollision();

    private Game game;

    public Player(final int x, final int y, final Id id, Handeler handeler, Game game) {
        super(x, y, id, handeler);
        this.game = game;
    }

    @Override public void tick() {
        int oldX = x;
        int oldY = y;
	if (velY <= 0){
	    x = Game.clamp(x + velX,0,Game.WIDTH-width);
	    if (handeler.hasCollision(this)) {
		x = oldX;
	    }
        }
	/*List<GameObject> collisionsX = handeler.getCollisions(this);
	if (!collisionsX.isEmpty()) {
	    x = oldX;
	    for (GameObject collision:
		    collisionsX) {
		collisionHandeler.collision(collision);
	    }
	}*/
	if (velY <= 0 || halfTick) {
	    y = Game.clamp(y + velY, 0, Game.HEIGHT - height);
	    List<GameObject> collisionsY = handeler.getCollisions(this);
	    if (!collisionsY.isEmpty()) {
		y = oldY;
		for (GameObject collision : collisionsY) {
		    collisionHandeler.collision(game, handeler, this, collision);
	        /*if (!collisionsX.contains(collision)) {
		    collisionHandeler.collision(collision);
		}*/
		}
	    }
	}
	halfTick = !halfTick;
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
