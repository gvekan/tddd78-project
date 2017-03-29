package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Trail;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;


public class Player extends ControlledObject implements Runnable
{
    private boolean halfTick = true;
    public static final int MAX_Y = Game.HEIGHT-100;

    public Player(final int x, final int y, Handler handler, Game game) {
        super(x, y, 20, 45, 200, Type.PLAYER,Color.CYAN, handler, new ControlledCollision(), game);
/*	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
		setX(Game.clamp(getX() + getVelX(), 0, Game.WIDTH - getWidth()));
		if(getVelX() != 0) {
		    addTrail();
		}
		controllCollision();
	    }
	};
	new Timer(10, taskPerformer).start();*/
    }

    @Override public void setVelY(final int velY) {
	if (y == MAX_Y && velY > 0) {
	    this.velY = 0;
	} else {
	    this.velY = velY;
	}
    }

    @Override public void setX(final int x) {
	this.x = Game.clamp(x, 0, Game.WIDTH - width);
    }

    @Override public void setY(final int y) {
	this.y = Game.clamp(y, 0, MAX_Y);
    }

    @Override public void tick() {
        setVelY(velY);
	if (halfTick) {
	    if (velY <= 0) {
		score += scorePerPixel - velY;
		setX(x + velX);
		addTrail();
	    }
	    setY(y + velY);
	}
	controllCollision();
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

    private void controllCollision() {

	Rectangle side = getLeftBound();
	List<GameObject> collisions = handler.getCollisions(side, this); //hasCollision left
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionAsGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.LEFT);
		}
	    }
	}

	side = getRightBound();
	collisions = handler.getCollisions(side, this); //hasCollision right
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionAsGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.RIGHT);
		}
	    }
	}

	side = getFrontBound();
	collisions = handler.getCollisions(side, this); //hasCollision front
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionAsGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.FRONT);
		}
	    }
	}

	side = getBackBound();
	collisions = handler.getCollisions(side, this); //hasCollision back
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionAsGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.BACK);
		}
	    }
	}
    }

    @Override public void run() {

    }
}
