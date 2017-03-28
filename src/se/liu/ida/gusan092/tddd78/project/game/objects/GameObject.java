package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup.PowerUp;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject
{
    protected int x, y, width, height, velX, velY;
    protected Type type;
    protected Handler handler;

    protected GameObject(final int x, final int y, final int width, final int height, final Type type, final Handler handler) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.type = type;
	this.handler = handler;
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

    public Type getType() {
	return type;
    }

    public void setType(final Type type) {
	this.type = type;
    }

    public Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
    }

    public boolean hasCollision(Rectangle r) {
        return r.intersects(getBounds());
    }

    public void powerUpCollision(PowerUp powerUp) {
        handler.remove(powerUp);
    }

    public abstract void collision(GameObject collision, Side side);
    public abstract void tick();
    public abstract void maxTick();
    public abstract void render(Graphics g);
}
