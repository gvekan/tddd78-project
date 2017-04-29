package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.collision.CollisionHandler;
import se.liu.ida.gusan092.tddd78.project.game.objects.collision.DefaultCollision;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Trail;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUp;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


public class Player extends GameObject
{
    public static final int WIDTH = 20;
    public static final int HEIGHT = 45;
    public static final int MAX_HEALTH = 10;
    private boolean halfTick = true;
    public static final int MAX_Y = Game.HEIGHT-150;
    private int maxHealth, health, score = 0, scorePerPixel = 1;
    private Game game;
    private CollisionHandler collisionHandler = new DefaultCollision();
    private List<PowerUp> powerUps = new ArrayList<>();
    private boolean alive = true;

    public Player(Handler handler, Game game) {
	super((Game.WIDTH - WIDTH) / 2, MAX_Y, WIDTH, HEIGHT, Color.CYAN, Type.PLAYER, handler);
	this.maxHealth = MAX_HEALTH;
	health = maxHealth;
	this.collisionHandler = collisionHandler;
	this.game = game;
    }

    @Override public void setVelY(final int velY) {
	if ((y == MAX_Y && velY > 0) || (y == 0 && velY <0)) {
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
		if (side.intersects(collision.getBounds())) { // Because a collisionWithGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.LEFT);
		}
	    }
	}

	side = getRightBound();
	collisions = handler.getCollisions(side, this); //hasCollision right
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionWithGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.RIGHT);
		}
	    }
	}

	side = getFrontBound();
	collisions = handler.getCollisions(side, this); //hasCollision front
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionWithGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.FRONT);
		}
	    }
	}

	side = getBackBound();
	collisions = handler.getCollisions(side, this); //hasCollision back
	if (!collisions.isEmpty()) {
	    for (int i = 0; i < collisions.size(); i++) {
		final GameObject collision = collisions.get(i);
		if (side.intersects(collision.getBounds())) { // Because a collisionWithGameObject can change player
		    collisionHandler.collision(game, handler, this, collision, Side.BACK);
		}
	    }
	}
    }

    @Override public String getSaveValues() {
        String sHalfTick;
        if (halfTick) sHalfTick = "true";
        else sHalfTick = "false";
        SavedProperties.getInstance().savePowerUps(powerUps);
        return super.getSaveValues() + SavedProperties.VALUE_SPLIT + sHalfTick + SavedProperties.VALUE_SPLIT + Integer.toString(health) + SavedProperties.VALUE_SPLIT
		+ Integer.toString(maxHealth) + SavedProperties.VALUE_SPLIT + Integer.toString(score) + SavedProperties.VALUE_SPLIT + Integer.toString(scorePerPixel);
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
    	if (this.health == 0) {
    	    setRunning(false);
    	    alive = false;
    	    handler.removeAfterTick(this);
    	}
        }

        public void addHealth(final int health) {
    	setHealth(this.health + health);
        }

        public int getScore() {
    	return score;
        }

        public void setScore(final int score) {
    	this.score = score;
        }

        public void addScore(final int score) {
            this.score = Game.clamp(this.score + score, 0, this.score + score + 1);
        }

        public int getScorePerPixel() {
    	return scorePerPixel;
        }

        public void setScorePerPixel(final int scorePerPixel) {
    	this.scorePerPixel = scorePerPixel;
        }

        public CollisionHandler getCollisionHandler() {
    	return collisionHandler;
        }

        public void setCollisionHandler(final CollisionHandler collisionHandler) {
    	this.collisionHandler = collisionHandler;
        }

        public void addPowerUp(final PowerUp powerUp) {
    	powerUps.add(powerUp);
        }

        public void removePowerUp(final PowerUp powerUp) {
    	powerUps.remove(powerUp);
        }

        public boolean isAlive() {
            return alive;
        }

        public void usePowerUps(){
            if (running) {
    	    for (int i = 0; i < powerUps.size(); i++) {
    		final PowerUp powerUp = powerUps.get(i);
    		powerUp.use();
    	    }
    	}
        }

        public void stopPowerUps(){
    	if (running) {
    	    for (int i = 0; i < powerUps.size(); i++) {
    		final PowerUp powerUp = powerUps.get(i);
    		powerUp.stop();
    	    }
    	}
        }


        @Override public void setRunning(final boolean running) {
            if (alive) {
    	    super.setRunning(running);
    	    for (int i = 0; i < powerUps.size(); i++) {
    		final PowerUp powerUp = powerUps.get(i);
    		powerUp.setRunning(running);
    	    }
    	}
        }

        public List<PowerUp> getPowerUps() {
            return powerUps;
        }

        protected void addTrail() {
    	game.getEnvironment().add(new Trail(x, y + height - 1, width, 2 - velY, color, game.getEnvironment()));
        }

        public void collisionWithPlayer(final Player collision, final Side side) {
    	collisionHandler.collisionWithPlayer(game, handler, this, collision, side);
        }

        @Override public void collisionWithGameObject(final GameObject collision, final Side side) {
    	collisionHandler.collision(game,handler,this, collision, side);
        }
}
