package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Trail;

import java.awt.Graphics2D;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Player extends ControlledObject
{
    private boolean halfTick = true;

    public Player(final int x, final int y, Handler handler, Game game) {
        super(x, y, 20, 45, 200, Type.PLAYER,Color.CYAN, handler, new ControlledCollision(), game);
    }

    @Override public void tick() {
	if (halfTick) {
	    if (velY <= 0) {
		score += scorePerPixel - velY;
		x = Game.clamp(x + velX, 0, Game.WIDTH - width);
		game.getEnvironment().add(new Trail(x, y+height-1, width, 2 - velY, game.getEnvironment(), this));
	    }
	    y = Game.clamp(y + velY, 0, Game.HEIGHT - height);
	}

	Rectangle side = getLeftBound();
	ArrayList<GameObject> collisions = handler.getCollisions(side, this); //hasCollision left
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collision(game, handler, this, collision, Side.LEFT);
		}
	    }
	}

	side = getRightBound();
	collisions = handler.getCollisions(side, this); //hasCollision right
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collision(game, handler, this, collision, Side.RIGHT);
		}
	    }
	}

	side = getFrontBound();
	collisions = handler.getCollisions(side, this); //hasCollision front
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collision(game, handler, this, collision, Side.FRONT);
		}
	    }
	}

	side = getBackBound();
	collisions = handler.getCollisions(side, this); //hasCollision back
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collision(game, handler, this, collision, Side.BACK);
		}
	    }
	}
	halfTick = !halfTick;
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(color);
	g2d.fill(getBounds());
    }

    public Rectangle getFrontBound() {return new Rectangle(x+1, y,width-2,1);}

    public Rectangle getBackBound() {return new Rectangle(x+1, y+height-1,width-2,1);}

    public Rectangle getLeftBound() {return new Rectangle(x, y+1,1,height-2);}

    public Rectangle getRightBound() {return new Rectangle(x+width-1, y+1,1,height-2);}
}
