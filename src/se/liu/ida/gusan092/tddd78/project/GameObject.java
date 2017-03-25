package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject
{
    protected int x, y, velX, velY;
    protected Id id;
    protected Handeler handeler;

    protected GameObject(final int x, final int y, final Id id, final Handeler handeler) {
	this.x = x;
	this.y = y;
	this.id = id;
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

    public Id getId() {
	return id;
    }

    public void setId(final Id id) {
	this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
}
