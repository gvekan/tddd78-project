package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.awt.Graphics;

public abstract class GameObject
{
    protected int x, y, width, height, velX, velY, maxHealth, health, score = 0;
    protected Identity identity;
    protected Handler handler;

    protected GameObject(final int x, final int y, final int width, final int height, final Identity identity, final Handler handler) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.identity = identity;
	this.handler = handler;
    }

    protected GameObject(final int x, final int y, final int width, final int height, final int maxHealth, final Identity identity, final Handler handler) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.maxHealth = maxHealth;
	health = maxHealth;
	this.identity = identity;
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

    public int getMaxHealth() {
	return maxHealth;
    }

    public void setMaxHealth(final int maxHealth) {
	this.maxHealth = maxHealth;
    }

    public int getHealth() {
	return health;
    }

    public void setHealth(final int health) {
	this.health = Game.clamp(health,0,maxHealth);
    }

    public void addHealth(int health) {
	this.health = Game.clamp(this.health + health,0,maxHealth);
    }

    public int getScore() {
	return score;
    }

    public void setScore(final int score) {
	this.score = score;
    }

    public void addScore(int score) {
        this.score = Game.clamp(this.score + score, 0, this.score + score + 1);
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

    public boolean hasCollision(Rectangle r) {
        return r.intersects(getBounds());
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
