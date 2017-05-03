package se.liu.ida.gusan092.tddd78.project.game.objects.still;


import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A still obstacle with horizontal movements, it changes direction when colliding with other obstacles
 */
public class Animal extends StillObject
{

    /**
     * The size of the animal
     */
    public static final int SIZE = 15;
    /**
     * Delay time of horizontal movements
     */
    public static final int DELAY = 25;
    /**
     * The red value of a RGB
     */
    public static final int RED = 160;
    /**
     * The green value of a RGB
     */
    public static final int GREEN = 82;
    /**
     * The blue value of a RGB
     */
    public static final int BLUE = 45;

    private Timer timer;

    /**
     * @param x the start x value
     * @param handler the handler it is in
     */
    public Animal(final int x, final Handler handler)
    {
	super(x, -SIZE, SIZE, SIZE, new Color(RED, GREEN, BLUE), Type.ANIMAL, handler);
	if ( x < Game.WIDTH/2) setVelX(1); // start going towards the middle
	else setVelX(-1);
	timer = null;
	createTimer();
    }

    /**
     * Used to restore a saved game
     * note that running is set false in GameObject
     */
    public Animal(final Handler handler, final String saveValues) {
    	super(SIZE, SIZE, new Color(RED, GREEN, BLUE), Type.ANIMAL, handler);
    	restoreSaveValues(saveValues);
	timer = null;
    	createTimer();
    }

    private void createTimer() {
	ActionListener taskPerformer = new ActionListener() {
	    public void actionPerformed(ActionEvent evt) {
	        if (running) {
		    setX(getX() + velX);
		    if (velX < 0) handleCollisionLeft();
		    else handleCollisionRight();
		}
	    }
	};
	timer = new Timer(DELAY, taskPerformer);
	timer.start();
    }

    /**
     * Handles possible collision with other GameObjects from the left
     */
    private void handleCollisionLeft() {
	List<GameObject> collisions = handler.getCollisions(getLeftBound(),this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		switch (collision.getType()) {
		    case ROADBLOCK:
			velX = -velX;
			x += velX;
			break;
		    case PLAYER:
			collision.collisionWithGameObject(collision, Side.LEFT);
			break;
		    case BULLET:
		        collision.collisionWithGameObject(this, Side.LEFT);
		        break;
		    case CONTAINER:
			handler.removeAfterTick(collision);
		}
	    }
	}
    }

    /**
     * @return a rectangle with the width of 1 and the height of the animal on the left side
     */
    private Rectangle getLeftBound() {return new Rectangle(x, y, 1, height);}

    /**
     * Handles possible collision with other GameObjects from the left
     */
    private void handleCollisionRight() {
	List<GameObject> collisions = handler.getCollisions(getRightBound(),this);
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		switch (collision.getType()) {
		    case ROADBLOCK:
		        velX = -velX;
		        x += velX;
		        break;
		    case PLAYER:
			collision.collisionWithGameObject(collision, Side.RIGHT);
			break;
		    case BULLET:
		        collision.collisionWithGameObject(this, Side.RIGHT);
		        break;
		    case CONTAINER:
			handler.removeAfterTick(collision);
		}
	    }
	}
    }


    /**
     * @return a rectangle with the width of 1 and the height of the animal on the right side
     */
    private Rectangle getRightBound() {return new Rectangle(x + width - 1, y, 1, height);}

    @Override public void tick() {
	super.tick();
	if (y == Game.HEIGHT) timer.stop();
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(color);
	g2d.fillOval(x,y,width,height);
    }

    /**
     * Not used because nothing has to be saved
     */
    @Override public void setStillSaveValues(final String[] saveValues) {

    }
}
