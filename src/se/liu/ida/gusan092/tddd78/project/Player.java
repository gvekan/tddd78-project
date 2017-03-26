package se.liu.ida.gusan092.tddd78.project;

import java.awt.Graphics2D;

import java.awt.*;
import java.awt.Rectangle;
import java.util.List;


public class Player extends GameObject
{
    private boolean halfTick = true;

    private CollisionHandeler collisionHandeler = new DefaultCollision();

    private Game game;

    public Player(final int x, final int y, final Identity identity, Handeler handeler, Game game) {
        super(x, y, 20, 45, identity, handeler);
        this.game = game;
    }

    @Override public void tick() {
	if (halfTick) {
	    if (velY <= 0) {
		x = Game.clamp(x + velX, 0, Game.WIDTH - width);
	    }
	    y = Game.clamp(y + velY, 0, Game.HEIGHT - height);
	}

	Rectangle side = getLeftBound();
	List<GameObject> collisions = handeler.getCollisions(side, identity); //hasCollision left
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandeler.collisionLeft(game, handeler, this, collision);
		}
	    }
	}

	side = getRightBound();
	collisions = handeler.getCollisions(side, identity); //hasCollision right
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandeler.collisionRight(game, handeler, this, collision);
		}
	    }
	}

	side = getFrontBound();
	collisions = handeler.getCollisions(side, identity); //hasCollision front
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandeler.collisionFront(game, handeler, this, collision);
		}
	    }
	}

	side = getBackBound();
	collisions = handeler.getCollisions(side, identity); //hasCollision back
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandeler.collisionBack(game, handeler, this, collision);
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

    public Rectangle getFrontBound() {return new Rectangle(x+1, y,width-2,1);}

    public Rectangle getBackBound() {return new Rectangle(x+1, y+height-1,width-2,1);}

    public Rectangle getLeftBound() {return new Rectangle(x, y+1,1,height-2);}

    public Rectangle getRightBound() {return new Rectangle(x+width-1, y+1,1,height-2);}
}
