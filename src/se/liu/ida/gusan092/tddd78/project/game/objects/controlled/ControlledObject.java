package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Trail;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup.PowerUp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ControlledObject extends GameObject
{
    protected int maxHealth, health, score = 0, scorePerPixel = 1;

    protected Game game;
    protected CollisionHandlerControlled collisionHandler;
    protected List<PowerUp> powerUps = new ArrayList<>();

    protected ControlledObject(final int x, final int y, final int width, final int height, final int maxHealth, final Type type, Color color,
			       final Handler handler, final CollisionHandlerControlled collisionHandler, final Game game)
    {
	super(x, y, width, height, color, type, handler);
	this.maxHealth = maxHealth;
	health = maxHealth;
	this.collisionHandler = collisionHandler;
	this.game = game;
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

    public int getScorePerPixel() {
	return scorePerPixel;
    }

    public void setScorePerPixel(final int scorePerPixel) {
	this.scorePerPixel = scorePerPixel;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(final Color color) {
	this.color = color;
    }

    public CollisionHandlerControlled getCollisionHandler() {
	return collisionHandler;
    }

    public void setCollisionHandler(final CollisionHandlerControlled collisionHandler) {
	this.collisionHandler = collisionHandler;
    }

    public void addPowerUp(PowerUp powerUp) {
	powerUps.add(powerUp);
    }

    public void removePowerUp(PowerUp powerUp) {
	powerUps.remove(powerUp);
    }

    public void usePowerUps(){
	for (int i = 0; i < powerUps.size(); i++) {
	    final PowerUp powerUp = powerUps.get(i);
	    powerUp.use();
	}
    }

    protected void addTrail() {
	game.getEnvironment().add(new Trail(x, y + height - 1, width, 2 - velY, game.getEnvironment(), this));
    }

    @Override public void collisionAsControlled(final ControlledObject collision, final Side side) {
	collisionHandler.collisionWithControlled(game, handler, this, collision, side);
    }

    @Override public void collisionAsGameObject(final GameObject collision, final Side side) {
	collisionHandler.collision(game,handler,this, collision, side);
    }

    @Override public void powerUpCollision(final PowerUp powerUp) {
	powerUp.activate(this);
    }
}
