package se.liu.ida.gusan092.tddd78.project;

import java.awt.Graphics2D;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Player extends GameObject
{
    private boolean halfTick = true;

    private CollisionHandler collisionHandler = new DefaultCollision();

    private Game game;

    private int scorePerHalfTick = 1;
    private List<Identity> noCollisionIdentities = new ArrayList<>();


    public Player(final int x, final int y, final Identity identity, Handler handler, Game game) {
        super(x, y, 20, 45, 200, identity, handler);
        this.game = game;
        setMaxHealth(200);
        noCollisionIdentities.add(Identity.ENVIROMENT);
	noCollisionIdentities.add(identity);
    }

    public void setScorePerHalfTick(final int scorePerHalfTick) {
	this.scorePerHalfTick = scorePerHalfTick;
    }

    @Override public void tick() {
	if (halfTick) {
	    if (velY <= 0) {
	        score += scorePerHalfTick - velY;
		x = Game.clamp(x + velX, 0, Game.WIDTH - width);
	    }
	    y = Game.clamp(y + velY, 0, Game.HEIGHT - height);
	}

	Rectangle side = getLeftBound();
	List<GameObject> collisions = handler.getCollisions(side, noCollisionIdentities); //hasCollision left
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collisionLeft(game, handler, this, collision);
		}
	    }
	}

	side = getRightBound();
	collisions = handler.getCollisions(side, noCollisionIdentities); //hasCollision right
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collisionRight(game, handler, this, collision);
		}
	    }
	}

	side = getFrontBound();
	collisions = handler.getCollisions(side, noCollisionIdentities); //hasCollision front
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collisionFront(game, handler, this, collision);
		}
	    }
	}

	side = getBackBound();
	collisions = handler.getCollisions(side, noCollisionIdentities); //hasCollision back
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collision can change player
		    collisionHandler.collisionBack(game, handler, this, collision);
		}
	    }
	}
	halfTick = !halfTick;
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(Color.CYAN);
	g2d.fill(getBounds());
	g.setColor(Color.RED);
	g.fillRect((Game.WIDTH-maxHealth)/2,Game.HEIGHT-100, maxHealth, 25);
	g.setColor(Color.GREEN);
	g.fillRect((Game.WIDTH-maxHealth)/2,Game.HEIGHT-100, health,25);

	g.setColor(Color.WHITE);
	g.drawString("Score: " + score,(Game.WIDTH+maxHealth+40)/2,Game.HEIGHT-80);
    }

    public void addHealth(int health) {
	this.health = Game.clamp(this.health + health, 0, maxHealth);
    }

    public void addScore(int score) {
	this.score += score;
    }

    public Rectangle getFrontBound() {return new Rectangle(x+1, y,width-2,1);}

    public Rectangle getBackBound() {return new Rectangle(x+1, y+height-1,width-2,1);}

    public Rectangle getLeftBound() {return new Rectangle(x, y+1,1,height-2);}

    public Rectangle getRightBound() {return new Rectangle(x+width-1, y+1,1,height-2);}
}
