package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject
{
    protected int x, y, width, height, velX, velY;
    protected Identity identity;
    protected Handeler handeler;

    protected GameObject(final int x, final int y, final int width, final int height, final Identity identity, final Handeler handeler) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.identity = identity;
	this.handeler = handeler;
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

    public Identity getIdentity() {
	return identity;
    }

    public void setIdentity(final Identity identity) {
	this.identity = identity;
    }

    public Rectangle getBounds() {
	return new Rectangle(x, y, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
