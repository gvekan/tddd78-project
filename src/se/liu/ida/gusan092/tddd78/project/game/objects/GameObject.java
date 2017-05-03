package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.awt.*;
import java.util.Arrays;

/**
 * The abstract class for objects on the gameboard (in a handler)
 */
public abstract class GameObject
{
    protected int x, y, width, height, velX, velY;
    protected Color color;
    protected Type type;
    protected Handler handler;
    protected boolean running;

    protected GameObject(final int x, final int y, final int width, final int height, final Color color, final Type type, final Handler handler)
    {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.color = color;
	this.type = type;
	this.handler = handler;
	running = true;
    }

    /**
     * Used to restore a saved game
     * Subclass should call restoreSaveValues(String)
     */
    protected GameObject(final int width, final int height, final Color color, final Type type, final Handler handler)
    {
	this.width = width;
	this.height = height;
	this.color = color;
	this.type = type;
	this.handler = handler;
	running = false; //Sets running to false because it is created from a saved file and timers in subclasses should not change values
    }

    public int getX() {
	return x;
    }

    public void setX(final int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public int getWidth() {
	return width;
    }

    public int getVelX() {
	return velX;
    }

    public void setVelX(final int velX) {
	this.velX = velX;
    }

    public int getVelY() {
	return velY;
    }

    public void setVelY(final int velY) {
	this.velY = velY;
    }

    public Color getColor() {
	return color;
    }

    public Type getType() {
	return type;
    }

    /**
     * Should be overrided but runned if a subclass has a value essential for its recreation
     * @return values that are essential for recreate a GameObject
     */
    public String getSaveValues() {
	return Integer.toString(type.getIndex()) + SavedProperties.ENUM_SPLIT + Integer.toString(x) +
	       SavedProperties.VALUE_SPLIT + Integer.toString(y) + SavedProperties.VALUE_SPLIT + Integer.toString(velX) +
	       SavedProperties.VALUE_SPLIT + Integer.toString(velY);
    }

    /**
     * Should be called in the subclasses  constructor for recreation
     * @param saveValues values from a saved file
     */
    protected void restoreSaveValues(String saveValues) {
	String[] values = saveValues.split(SavedProperties.VALUE_SPLIT);
	x =Integer.parseInt(values[0]);
	y =Integer.parseInt(values[1]);
	velX =Integer.parseInt(values[2]);
	velY =Integer.parseInt(values[3]);

	restoreSaveValues(Arrays.copyOfRange(values, 4, values.length));
    }

    /**
     * Used by GameObject.restoreSaveValues
     * @param saveValues the rest of the values belonging subclass
     */
    protected abstract void restoreSaveValues(final String[] saveValues);

    /**
     * @return a rectangle representing the shape of the GameObjects used to detect collision
     * Should not be used if GameObject has another shape then a rectangle
     */
    protected Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
    }

    /**
     * Should be overrided if GameObject has another shape then a rectangle for more precise collision detection
     */
    public boolean hasCollision(final Rectangle r) {
	return r.intersects(getBounds());
    }

    public void setRunning(boolean running) {
	this.running = running;
    }

    /**
     * Used by GameObject when colliding with Players or Bullets
     * @param collision the GameObject that has detected the collision
     * @param side the side it collided with, use Side.FRONT if it is unknown
     * Will throw a error if it is used for a GameObject that would not need it
     */
    public void collisionWithGameObject(final GameObject collision, final Side side) {
	throw new AssertionError("Only to be used with gameobjects with collision handler");
    }


    /**
     * Used by Players when colliding with other Players or Bullets
     * @param collision the Player that has detected the collision
     * @param side the side it collided with, use Side.FRONT if it is unknown
     * Will throw a error if it is used for a GameObject that would not need it
     */
    public void collisionWithPlayer(final Player collision, final Side side) {
	throw new AssertionError("Only to be used with gameobjects with collision handler");
    }

    public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(color);
	g2d.fill(getBounds());
    }

    public abstract void tick();
}
