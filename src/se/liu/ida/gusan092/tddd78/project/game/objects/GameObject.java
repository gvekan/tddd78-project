package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.awt.*;
import java.util.Arrays;

public abstract class GameObject
{
    protected int x, y, width, height, velX, velY;
    protected Color color;
    protected Type type;
    protected Handler handler;
    protected boolean running = true;

    protected GameObject(final int x, final int y, final int width, final int height, final Color color, final Type type, final Handler handler) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.color = color;
	this.type = type;
	this.handler = handler;
    }

    protected GameObject(final int width, final int height, Color color, final Type type, final Handler handler, final String saveValues) {
	this.width = width;
	this.height = height;
	this.color = color;
	this.type = type;
	this.handler = handler;
	running = false;
	String[] values = saveValues.split(SavedProperties.VALUE_SPLIT);
	x = Integer.parseInt(values[0]);
	y = Integer.parseInt(values[1]);
	velX = Integer.parseInt(values[2]);
	velY = Integer.parseInt(values[3]);
	setSaveValues(Arrays.copyOfRange(values, 4, values.length));
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

    public void setY(final int y) {
	this.y = y;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(final int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(final int height) {
	this.height = height;
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

    public void setColor(final Color color) {
	this.color = color;
    }

    public Type getType() {
	return type;
    }

    public void setType(final Type type) {
	this.type = type;
    }

    public String getSaveValues() {
        return Integer.toString(type.getIndex()) + SavedProperties.ENUM_SPLIT + Integer.toString(x) + SavedProperties.VALUE_SPLIT + Integer.toString(y)
	       + SavedProperties.VALUE_SPLIT + Integer.toString(velX) + SavedProperties.VALUE_SPLIT + Integer.toString(velY);
    }

    public Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
    }

    public boolean hasCollision(final Rectangle r) {
        return r.intersects(getBounds());
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    /*public void powerUpCollision(PowerUp powerUp) {
        handler.removeAfterTick(powerUp);
    }*/

    public void collisionWithGameObject(final GameObject collision, final Side side) {
	throw new AssertionError("Only to be used with gameobjects with collision handler");
    }
    public void collisionWithPlayer(final Player collision, final Side side) {
	throw new AssertionError("Only to be used with gameobjects with collision handler");
    }

    public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(color);
	g2d.fill(getBounds());
    }

    public abstract void tick();
    public abstract void setSaveValues(final String[] saveValues);
}
